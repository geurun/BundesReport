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

	public HomeBean(MessageSource msgSrc, User user, List<Post> freePosts, List<Post> livingPosts, List<Post> fleaPosts,
			List<Post> jobPosts) {
		super(msgSrc, user);
		this.freePosts = freePosts;
		this.livingPosts = livingPosts;
		this.fleaPosts = fleaPosts;
		this.jobPosts = jobPosts;

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
