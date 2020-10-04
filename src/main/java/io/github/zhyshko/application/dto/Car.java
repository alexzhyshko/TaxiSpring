package io.github.zhyshko.application.dto;

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
public class Car {
	private Integer id;
	private String plate;
	private String manufacturer;
	private String model;
	private String category;
	private String status;
	private Integer passengerCount;
	private Float priceMultiplier;
	private Coordinates coordinates;
}
