package io.github.zhyshko.application.converter.entity2dto;

public class RouteEntityToDTOConverter {

	private RouteEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Route convertToDto(io.github.zhyshko.application.entity.Route entity){
		return io.github.zhyshko.application.dto.Route.builder()
				.id(entity.getId())
				.departure(CoordinatesEntityToDTOConverter.convertToDto(entity.getDeparture()))
				.destination(CoordinatesEntityToDTOConverter.convertToDto(entity.getDestination()))
				.distance(entity.getDistance())
				.time(entity.getTime())
				.build();
	}
	
}
