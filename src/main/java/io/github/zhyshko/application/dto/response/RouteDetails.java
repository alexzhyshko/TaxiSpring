package io.github.zhyshko.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RouteDetails {
	private float price;
	private int arrivalTime;
	private String categoryLocaleName;
	
	
}
