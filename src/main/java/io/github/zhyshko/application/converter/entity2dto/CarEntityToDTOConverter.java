package io.github.zhyshko.application.converter.entity2dto;

import java.util.List;
import java.util.stream.Collectors;

public class CarEntityToDTOConverter {

	private CarEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Car convertToDto(io.github.zhyshko.application.entity.Car entity, String userLocale){
		return io.github.zhyshko.application.dto.Car.builder()
				.id(entity.getId())
				.plate(entity.getPlate())
				.manufacturer(entity.getManufacturer())
				.model(entity.getModel())
				.category(TranslationsConverter.extractLocalizedText(entity.getCategory().getTranslation(), userLocale))
				.status(TranslationsConverter.extractLocalizedText(entity.getStatus().getTranslation(), userLocale))
				.passengerCount(entity.getPassengerCount())
				.priceMultiplier(entity.getPriceMultiplier())
				.coordinates(CoordinatesEntityToDTOConverter.convertToDto(entity.getCoordinates()))
				.build();
	}
	
	public static List<io.github.zhyshko.application.dto.Car> convertToDto(List<io.github.zhyshko.application.entity.Car> entities, String userLocale){
		return entities.stream().map(entity->convertToDto(entity, userLocale)).collect(Collectors.toList());
	}
	
}
