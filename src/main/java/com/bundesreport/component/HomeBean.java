package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Post;
import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeBean extends MessageBean {

	public HomeBean(MessageSource msgSrc, Authentication auth, PostService postService) {
		super(msgSrc, auth);
		this.freePosts = postService.findTop5ByCategory(CategoryType.FREE_BOARD);
		this.livingPosts = postService.findTop5ByCategory(CategoryType.LIVING_QA);
		this.fleaPosts = postService.findTop5ByCategory(CategoryType.FLEA_MARKET);
		this.jobPosts = postService.findTop5ByCategory(CategoryType.JOB_SEARCH);

		freeBoard = getMsgUtil().getMessage("home.freeBoard");
		livingQA = getMsgUtil().getMessage("home.livingQA");
		fleaMarket = getMsgUtil().getMessage("home.fleaMarket");
		jobSearch = getMsgUtil().getMessage("home.jobSearch");

		btnMessageSend = getMsgUtil().getMessage("home.btnMessageSend");
	}

	private List<Post> freePosts;
	private List<Post> livingPosts;
	private List<Post> fleaPosts;
	private List<Post> jobPosts;

	private String freeBoard;
	private String livingQA;
	private String fleaMarket;
	private String jobSearch;

	private String btnMessageSend;
}
