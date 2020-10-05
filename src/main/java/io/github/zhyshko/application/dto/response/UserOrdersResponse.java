package io.github.zhyshko.application.dto.response;

import java.util.List;

import io.github.zhyshko.application.dto.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserOrdersResponse {

	private List<Order> orders;
	private int numberOfPages;
	
	
}
