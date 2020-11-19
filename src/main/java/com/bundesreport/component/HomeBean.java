package com.bundesreport.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HomeBean extends MessageBean{

	public HomeBean(MessageSource msgSrc, User user, List<Post> posts) {
		super(msgSrc, user);
		this.posts = posts;
		
		freeBoard = msgUtil.getMessage("home.freeBoard");
		livingQA = msgUtil.getMessage("home.livingQA");
	}
	
	private List<Post> posts;
	
	private String freeBoard;
	private String livingQA;
}
