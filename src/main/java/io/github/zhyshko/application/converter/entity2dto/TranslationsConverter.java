package io.github.zhyshko.application.converter.entity2dto;

import io.github.zhyshko.application.entity.Translation;
import io.github.zhyshko.application.exception.LocaleNotSupportedException;

public class TranslationsConverter {

	private TranslationsConverter() {}
	
	public static String extractLocalizedText(Translation translation, String locale) {
		switch (locale.toUpperCase()) {
		case "UA":
			return translation.getText_UA();
		case "RU":
			return translation.getText_RU();
		case "EN":
			return translation.getText_EN();
		default:
			throw new LocaleNotSupportedException("Locale "+locale+" is not supported");
		}
	}
	
}
