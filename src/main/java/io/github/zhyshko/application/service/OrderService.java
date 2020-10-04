package io.github.zhyshko.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.dto.misc.PaginationFilteringSortingDTO;
import io.github.zhyshko.application.dto.request.OrderCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Coordinates;
import io.github.zhyshko.application.entity.Driver;
import io.github.zhyshko.application.entity.Driving;
import io.github.zhyshko.application.entity.Order;
import io.github.zhyshko.application.entity.Route;
import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.exception.RouteNotFoundException;
import io.github.zhyshko.application.exception.SomethingWentWrongException;
import io.github.zhyshko.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private static final int STANDART_FEE_PER_KILOMETER = 5;
	private static final int BASE_RIDE_PRICE = 25;
	private static final int MINIMAL_RIDE_PRICE = 40;
	
	private final AuthService authService;
	private final CarService carService;
	private final DriverService driverService;
	private final UserService userService;
	private final RouteService routeService;
	private final OrderRepository orderRepository;
	
	public UserOrdersResponse getAllOrdersPagedAndSortedAndFiltered(PaginationFilteringSortingDTO dto) {

	}

	public Order tryCreateOrder(OrderCreateRequest routeDetails) {
		Coordinates departure = Coordinates.builder().longitude(routeDetails.getDepartureLongitude()).latitude(routeDetails.getDepartureLatitude()).build();
		Coordinates destination = Coordinates.builder().longitude(routeDetails.getDestinationLongitude()).latitude(routeDetails.getDestinationLatitude()).build();
		Route route = routeService.tryGetRoute(departure, destination).orElseThrow(()->new RouteNotFoundException("Could not build route"));
		String username = authService.getCurrentAuthenticatedUsername().orElseThrow(()->new SomethingWentWrongException("User is not authenticated"));
		User user = userService.findUserByUsername(username);
		Car car = carService.getNearestCarByPlacesCountAndCategory(departure, routeDetails.getNumberOfPassengers(), routeDetails.getCarCategory());
		Driver driver = driverService.getDriverByCarToday(car);
		Route carArrivalRoute = this.routeService.tryGetRoute(departure, car.getCoordinates()).orElseThrow(()->new RouteNotFoundException("Could not build driver arrival route"));
		Order order = this.orderRepository.save(Order.builder()
				.route(route)
				.price(calculateFinalOrderPrice(route, car, this.orderRepository.findAllByUserId(user.getId())))
				.user(user)
				.driving(Driving.builder().car(car).driver(driver).build())
				.timeToArrival(carArrivalRoute.getTime())
				.dateOfOrder(LocalDateTime.now())
				.build());
	}

	public List<RouteDetails> getAvailableRoutesDetails(OrderCreateRequest routeDetails) {

	}
	
	public UserOrdersResponse getOrdersByUserIdPaginated(UUID userId, Integer page, String type) {

	}
	
	public boolean tryFinishOrderById(Integer orderId) {
		
	}
	
	private int calculateFinalOrderPrice(Route route, Car car, List<Order> userPreviousOrders) {
		return getRouteRawPrice(route, car)-getLoyaltyDiscount(userPreviousOrders);
	}
	
	private int getRouteRawPrice(Route route, Car car) {
		int price = Math.round(route.getDistance()*car.getPriceMultiplier()*STANDART_FEE_PER_KILOMETER)+BASE_RIDE_PRICE;
		return price<MINIMAL_RIDE_PRICE?MINIMAL_RIDE_PRICE:price; 
	}
	
	private int getLoyaltyDiscount(List<Order> userPreviousOrders) {
		long totalOrderSum = Math.round(userPreviousOrders.stream().mapToDouble(Order::getPrice).sum());
		return Math.round(totalOrderSum*0.01f);
	}
	
}
