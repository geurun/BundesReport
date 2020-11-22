package com.bundesreport.component;

import java.util.Locale;
import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.User;
import com.bundesreport.util.MsgUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageBean {

	private User user;
	private Locale locale = Locale.ROOT;
	private MsgUtil msgUtil;
	private String title;

	public MessageBean(MessageSource msgSrc, Authentication auth) {
		if (Objects.nonNull(auth)) {
			this.user = (User) auth.getPrincipal();
			this.locale = user.getLanguageStatus().getLocale();
		}
		this.msgUtil = new MsgUtil(msgSrc, locale);
	}
}
