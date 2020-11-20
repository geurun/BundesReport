package com.bundesreport.component;

import java.util.List;
import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListBean extends MessageBean {

	public PostListBean(MessageSource msgSrc, User user, List<Post> posts) {
		super(msgSrc, user);
		this.posts = posts;
		setNames();
	}

	public PostListBean(MessageSource msgSrc, User user, List<Post> posts, String category) {
		super(msgSrc, user);
		this.posts = posts;
		this.category = CategoryType.valueOf(category.toUpperCase());
		setNames();
	}

	private void setNames() {
		if (Objects.isNull(this.category)) {
			setTitle(getMsgUtil().getMessage("post.title"));
		} else {
			setTitle(getMsgUtil().getMessage(this.category.getMessageCode()));
		}
		number = getMsgUtil().getMessage("post.list.number");
		postTitle = getMsgUtil().getMessage("post.list.postTitle");
		createdUser = getMsgUtil().getMessage("post.list.createdUser");
		createdDate = getMsgUtil().getMessage("post.list.createdDate");
		write = getMsgUtil().getMessage("post.list.write");
	}

	private List<Post> posts;
	private CategoryType category;
	private String number;
	private String postTitle;
	private String createdUser;
	private String createdDate;
	private String write;
}
