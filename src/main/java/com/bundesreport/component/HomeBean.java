package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeBean extends MessageBean {

	public HomeBean(MessageSource msgSrc, User user, PostService postService) {
		super(msgSrc, user);
		this.freePosts = postService.findByCategory(CategoryType.FREE_BOARD);
		this.livingPosts = postService.findByCategory(CategoryType.LIVING_QA);
		this.fleaPosts = postService.findByCategory(CategoryType.FLEA_MARKET);
		this.jobPosts = postService.findByCategory(CategoryType.JOB_SEARCH);

		freeBoard = getMsgUtil().getMessage("home.freeBoard");
		livingQA = getMsgUtil().getMessage("home.livingQA");
		fleaMarket = getMsgUtil().getMessage("home.fleaMarket");
		jobSearch = getMsgUtil().getMessage("home.jobSearch");
	}

	private List<Post> freePosts;
	private List<Post> livingPosts;
	private List<Post> fleaPosts;
	private List<Post> jobPosts;

	private String freeBoard;
	private String livingQA;
	private String fleaMarket;
	private String jobSearch;
}
