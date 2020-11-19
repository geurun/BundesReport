package com.bundesreport.component;

import java.util.Locale;
import java.util.Objects;

import org.springframework.context.MessageSource;

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

	public MessageBean(MessageSource msgSrc, User user) {
		if (Objects.nonNull(user)) {
			this.user = user;
			this.locale = user.getLanguageStatus().getLocale();
		}
		this.msgUtil = new MsgUtil(msgSrc, locale);
	}
}
