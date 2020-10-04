package io.github.zhyshko.application.converter.entity2dto;

public class RouteEntityToDTOConverter {

	private RouteEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Route convertToDto(io.github.zhyshko.application.entity.Route entity){
		return io.github.zhyshko.application.dto.Route.builder()
				.departure(CoordinatesEntityToDTOConverter.convertToDto(entity.departure))
				.destination(CoordinatesEntityToDTOConverter.convertToDto(entity.destination))
				.distance(entity.distance)
				.time(entity.time)
				.build();
	}
	
}
