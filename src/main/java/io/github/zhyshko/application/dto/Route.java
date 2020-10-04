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
public class Route {
	private Integer id;
	public Coordinates departure;
	public Coordinates destination;
	public Float distance;
	public Integer time;
}
