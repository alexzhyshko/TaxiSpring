package io.github.zhyshko.application.converter.entity2dto;

public class OrderEntityToDTOConverter {

	private OrderEntityToDTOConverter() {}
	
	public static io.github.zhyshko.application.dto.Order convertToDto(io.github.zhyshko.application.entity.Order entity, String userLocale){
		return io.github.zhyshko.application.dto.Order.builder()
				.route(RouteEntityToDTOConverter.convertToDto(entity.getRoute()))
				.price(entity.getPrice())
				.user(UserEntityToDTOConverter.convertToDto(entity.getUser()))
				.car(CarEntityToDTOConverter.convertToDto(entity.getDriving().getCar(), userLocale))
				.driver(DriverEntityToDTOConverter.convertToDto(entity.getDriving().getDriver(), userLocale))
				.timeToArrival(entity.getTimeToArrival())
				.dateOfOrder(entity.getDateOfOrder())
				.status(TranslationsConverter.extractLocalizedText(entity.getStatus().getTranslation(), userLocale))
				.statusid(entity.getStatus().getId())
				.build();
	}
	
}
