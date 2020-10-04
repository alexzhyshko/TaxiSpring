package io.github.zhyshko.application.converter.entity2dto;

public class DriverEntityToDTOConverter {

	private DriverEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Driver convertToDto(io.github.zhyshko.application.entity.Driver entity, String userLocale){
		return io.github.zhyshko.application.dto.Driver.builder()
				.id(entity.getId())
				.name(entity.getName())
				.surname(entity.getSurname())
				.rating(entity.getRating())
				.build();
	}
	
}
