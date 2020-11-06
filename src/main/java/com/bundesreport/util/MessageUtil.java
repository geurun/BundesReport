package com.bundesreport.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageUtil {

	public String getMessage(MessageSource msgSource, String msgCode, Locale locale) {
		return getMessage(msgSource, msgCode, null, locale);
	}

	public String getMessage(MessageSource msgSource, String msgCode, String[] stringList, Locale locale) {
		if (msgSource.equals(null)) {
			return msgCode;
		}
		if (locale.equals(null)) {
			return msgSource.getMessage(msgCode, stringList, Locale.ROOT);
		}
		return msgSource.getMessage(msgCode, stringList, locale);
	}
}
