package com.bundesreport.type;

import java.util.Locale;

public enum LanguageStatus {
	KO("KOREAN"), DE("DEUTSCH");

	private String value;

	LanguageStatus(String value) {
		this.value = value;
	}

	public Locale getLocale() {
		if (this.value == "KOREAN") {
			return Locale.KOREAN;
		}
		return Locale.GERMAN;
	}
}
