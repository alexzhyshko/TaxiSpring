package io.github.zhyshko.application.converter.entity2dto;

public class UserEntityToDTOConverter {

	private UserEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.User convertToDto(io.github.zhyshko.application.entity.User entity){
		return io.github.zhyshko.application.dto.User.builder()
				.username(entity.getUsername())
				.name(entity.getName())
				.surname(entity.getSurname())
				.rating(entity.getRating())
				.build();
	}
	
}
