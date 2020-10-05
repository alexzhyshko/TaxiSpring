package io.github.zhyshko.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.github.zhyshko.application.converter.entity2dto.OrderEntityToDTOConverter;
import io.github.zhyshko.application.converter.entity2dto.TranslationsConverter;
import io.github.zhyshko.application.dto.CarCategory;
import io.github.zhyshko.application.dto.misc.PaginationFilteringSortingDTO;
import io.github.zhyshko.application.dto.request.OrderCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Coordinates;
import io.github.zhyshko.application.entity.Driving;
import io.github.zhyshko.application.entity.Order;
import io.github.zhyshko.application.entity.OrderStatus;
import io.github.zhyshko.application.entity.Route;
import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.exception.DriverForCarNotFoundException;
import io.github.zhyshko.application.exception.NoCarsFoundException;
import io.github.zhyshko.application.exception.RouteNotFoundException;
import io.github.zhyshko.application.exception.SomethingWentWrongException;
import io.github.zhyshko.application.repository.DrivingRepository;
import io.github.zhyshko.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private static final int STANDART_FEE_PER_KILOMETER = 5;
	private static final int BASE_RIDE_PRICE = 25;
	private static final int MINIMAL_RIDE_PRICE = 40;

	private static final int ADMIN_ORDER_COUNT_PER_PAGE = 15;
	private static final int USER_ORDER_COUNT_PER_PAGE = 4;

	private final AuthService authService;
	private final CarService carService;
	private final UserService userService;
	private final RouteService routeService;
	private final OrderRepository orderRepository;
	private final DrivingRepository drivingRepository;

	
	@Transactional
	public UserOrdersResponse getAllOrdersPagedAndSortedAndFiltered(PaginationFilteringSortingDTO dto,
			String userLocale) {
		Pageable page = sortOrdersListIfNeeded(dto);
		Page<Order> ordersPage = this.orderRepository.findAll(page);
		List<Order> orders = ordersPage.getContent();
		filterOrdersListIfNeeded(orders, dto);
		return UserOrdersResponse.builder().numberOfPages(ordersPage.getTotalPages())
				.orders(OrderEntityToDTOConverter.convertToDto(orders, userLocale)).build();
	}
	

	private Pageable sortOrdersListIfNeeded(PaginationFilteringSortingDTO dto) {
		if (dto.isSort()) {
			return PageRequest.of(dto.getPage(), ADMIN_ORDER_COUNT_PER_PAGE, getSortingOrder(dto));
		}
		return PageRequest.of(dto.getPage(), ADMIN_ORDER_COUNT_PER_PAGE);
	}
	
	private Sort getSortingOrder(PaginationFilteringSortingDTO dto) {
		Sort result = Sort.by(dto.getSortBy());
		if(dto.getSortOrder().equalsIgnoreCase("asc")) {
			return result;
		}
		return result.descending();
	}
	
	
	private void filterOrdersListIfNeeded(List<Order> orders, PaginationFilteringSortingDTO dto) {
		if (dto.isFilter()) {
			orders = filterOrdersByUsernameIfNeeded(orders, dto);
			orders = filterOrdersByDateIfNeeded(orders, dto);
		}
	}

	private List<Order> filterOrdersByUsernameIfNeeded(List<Order> orders, PaginationFilteringSortingDTO dto) {
		if (dto.getFilterBy().equalsIgnoreCase("username")) {
			return orders.stream().filter(order -> order.getUser().getUsername().equalsIgnoreCase(dto.getValue()))
					.collect(Collectors.toList());
		}
		return orders;
	}

	private List<Order> filterOrdersByDateIfNeeded(List<Order> orders, PaginationFilteringSortingDTO dto) {
		if (dto.getFilterBy().equalsIgnoreCase("date")) {
			return orders.stream().filter(order -> order.getUser().getUsername().equalsIgnoreCase(dto.getValue()))
					.collect(Collectors.toList());
		} 
		return orders;
	}

	@Transactional
	public Order tryCreateOrder(OrderCreateRequest routeDetails) {
		Coordinates departure = buildDepartureCoordinates(routeDetails);
		Coordinates destination = buildDestinationCoordinates(routeDetails);
		Route route = routeService.tryGetRoute(departure, destination)
				.orElseThrow(() -> new RouteNotFoundException("Could not build route"));
		String username = authService.getCurrentAuthenticatedUsername()
				.orElseThrow(() -> new SomethingWentWrongException("User is not authenticated"));
		User user = userService.findUserByUsername(username);
		Car car = carService.getNearestCarByPlacesCountAndCategory(departure, routeDetails.getNumberOfPassengers(),
				routeDetails.getCarCategory());
		Driving driving = this.drivingRepository.findByCarAndDayOfDriving(car, LocalDate.now())
				.orElseThrow(() -> new DriverForCarNotFoundException("Could not find a driver for a car today"));
		Route carArrivalRoute = this.routeService.tryGetRoute(departure, car.getCoordinates())
				.orElseThrow(() -> new RouteNotFoundException("Could not build driver arrival route"));
		Order order = this.orderRepository.save(Order.builder().route(route)
				.price(calculateFinalOrderPrice(route, car, getPreviousUserOrders(user.getId()))).user(user)
				.driving(driving).timeToArrival(carArrivalRoute.getTime()).dateOfOrder(LocalDateTime.now())
				.status(getOrderStatusById(1)).build());
		this.carService.setCarBusy(car);
		return this.orderRepository.save(order);
	}

	private Coordinates buildDepartureCoordinates(OrderCreateRequest routeDetails) {
		return Coordinates.builder().longitude(routeDetails.getDepartureLongitude())
				.latitude(routeDetails.getDepartureLatitude()).build();
	}

	private Coordinates buildDestinationCoordinates(OrderCreateRequest routeDetails) {
		return Coordinates.builder().longitude(routeDetails.getDestinationLongitude())
				.latitude(routeDetails.getDestinationLatitude()).build();
	}

	
	@Transactional
	public List<RouteDetails> getAvailableRoutesDetailsByEveryCategory(OrderCreateRequest routeDetails,
			String userLocale) {
		List<RouteDetails> result = new ArrayList<>();
		Coordinates departureCoordinates = buildDepartureCoordinates(routeDetails);
		Coordinates destinationCoordinates = buildDestinationCoordinates(routeDetails);
		Route customerRoute = this.routeService.tryGetRoute(departureCoordinates, destinationCoordinates)
				.orElseThrow(() -> new RouteNotFoundException("Could not build route"));
		for (CarCategory category : CarCategory.values()) {
			try {
				Car nearestCarByCategory = this.carService.getNearestCarByPlacesCountAndCategory(departureCoordinates,
						routeDetails.getNumberOfPassengers(), category.toString());
				Route carArrivalRoute = this.routeService
						.tryGetRoute(nearestCarByCategory.getCoordinates(), departureCoordinates)
						.orElseThrow(() -> new RouteNotFoundException("Could not build route"));
				result.add(RouteDetails.builder()
						.arrivalTime(carArrivalRoute.getTime())
						.categoryLocaleName(TranslationsConverter
								.extractLocalizedText(nearestCarByCategory.getCategory().getTranslation(), userLocale))
						.price(getRouteRawPrice(customerRoute, nearestCarByCategory)).build());
			} catch (NoCarsFoundException e) {

			}
		}

		return result;
	}

	
	@Transactional
	public UserOrdersResponse getOrdersByUserIdPaginated(UUID userId, Integer pageNumber, String userLocale) {
		Pageable page = PageRequest.of(pageNumber, USER_ORDER_COUNT_PER_PAGE);
		User user = this.userService.getUserById(userId);
		Page<Order> ordersPage = this.orderRepository.findAllByUserOrderByDateOfOrderDesc(user, page);
		List<Order> orders = ordersPage.getContent();
		return UserOrdersResponse.builder().numberOfPages(ordersPage.getTotalPages())
				.orders(OrderEntityToDTOConverter.convertToDto(orders, userLocale)).build();
	}

	
	@Transactional
	public boolean tryFinishOrderById(Integer orderId) {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new SomethingWentWrongException("Could not find an order by id"));
		setOrderFinished(order);
		Car car = order.getDriving().getCar();
		this.carService.setCarFree(car);
		return true;
	}

	private void setOrderFinished(Order order) {
		order.setStatus(getOrderStatusById(2));
		this.orderRepository.save(order);
	}

	private OrderStatus getOrderStatusById(int statusId) {
		return this.orderRepository.findOrderStatusById(statusId);
	}

	private List<Order> getPreviousUserOrders(UUID userId) {
		return this.orderRepository.findAllByUserId(userId);
	}

	private Float calculateFinalOrderPrice(Route route, Car car, List<Order> userPreviousOrders) {
		return getRouteRawPrice(route, car) - getLoyaltyDiscount(userPreviousOrders) * 1f;
	}

	private int getRouteRawPrice(Route route, Car car) {
		int price = Math.round(route.getDistance() * car.getPriceMultiplier() * STANDART_FEE_PER_KILOMETER)
				+ BASE_RIDE_PRICE;
		return price < MINIMAL_RIDE_PRICE ? MINIMAL_RIDE_PRICE : price;
	}

	private int getLoyaltyDiscount(List<Order> userPreviousOrders) {
		long totalOrderSum = Math.round(userPreviousOrders.stream().mapToDouble(Order::getPrice).sum());
		return Math.round(totalOrderSum * 0.01f);
	}

}
