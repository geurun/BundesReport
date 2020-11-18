package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListBean extends MessageBean {

	public PostListBean(MessageSource msgSrc, User user, List<Post> posts, String category) {
		super(msgSrc, user);
		this.posts = posts;
		this.category = CategoryType.valueOf(category.toUpperCase());

		title = msgUtil.getMessage(this.category.getMessageCode());
		number = msgUtil.getMessage("post.list.number");
		postTitle = msgUtil.getMessage("post.list.postTitle");
		createdUser = msgUtil.getMessage("post.list.createdUser");
		createdDate = msgUtil.getMessage("post.list.createdDate");
		write = msgUtil.getMessage("post.list.write");
	}

	private List<Post> posts;
	private CategoryType category;
	private String number;
	private String postTitle;
	private String createdUser;
	private String createdDate;
	private String write;
}
