package com.bundesreport.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MsgUtil {

	MessageSource msgSrc;
	Locale locale;

	public MsgUtil(MessageSource msgSrc) {
		this.msgSrc = msgSrc;
		this.locale = Locale.ROOT;
	}

	public MsgUtil(MessageSource msgSrc, Locale locale) {
		this.msgSrc = msgSrc;
		this.locale = locale;
	}

	public String getMessage(String msgCode) {
		return getMessage(msgCode, null);
	}

	public String getMessage(String msgCode, String[] list) {
		try {
			return msgSrc.getMessage(msgCode, list, locale);
		} catch (Exception e) {
			return msgCode;
		}
	}
}
