package io.github.zhyshko.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {

	private Integer id;
	private Route route;
	private Float price;
	private User user;
	private Car car;
	private Driver driver;
	private Integer timeToArrival;
	private LocalDateTime dateOfOrder;
	private String status;
	private Integer statusid;

}
