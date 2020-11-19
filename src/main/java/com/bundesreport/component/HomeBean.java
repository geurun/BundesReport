package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeBean extends MessageBean {

	public HomeBean(MessageSource msgSrc, User user, List<Post> posts) {
		super(msgSrc, user);
		this.posts = posts;

		freeBoard = getMsgUtil().getMessage("home.freeBoard");
		livingQA = getMsgUtil().getMessage("home.livingQA");
	}

	private List<Post> posts;

	private String freeBoard;
	private String livingQA;
}
