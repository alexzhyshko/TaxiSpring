package io.github.zhyshko.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.dto.PaginationFilteringSortingDTO;
import io.github.zhyshko.application.dto.request.RouteCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.entity.Order;

@Service
public class OrderService {

	public UserOrdersResponse getAllOrdersPagedAndSortedAndLocalized(PaginationFilteringSortingDTO dto, String userLocale) {

	}

	public Order tryCreateRouteLocalized(RouteCreateRequest routeDetails, String userLocale) {

	}

	public List<RouteDetails> getAvailableRoutesDetailsLocalized(RouteCreateRequest routeDetails, String userLocale) {

	}
	
	public UserOrdersResponse getOrdersByUserIdPaginatedLocalized(UUID userId, Integer page, String type, String userLocale) {

	}
	
	public boolean tryFinishOrderByIdLocalized(Integer orderId, String userLocale) {
		
	}
	

}
