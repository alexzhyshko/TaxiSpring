package io.github.zhyshko.application.converter.entity2dto;

public class CoordinatesEntityToDTOConverter {

	private CoordinatesEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Coordinates convertToDto(io.github.zhyshko.application.entity.Coordinates entity){
		return io.github.zhyshko.application.dto.Coordinates.builder()
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.build();
	}
	
}
