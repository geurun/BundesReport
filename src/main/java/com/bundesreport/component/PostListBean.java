package com.bundesreport.component;

import java.util.List;
import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Post;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListBean extends MessageBean {

	public PostListBean(MessageSource msgSrc, Authentication auth, List<Post> posts, boolean isComeFromMenu) {
		super(msgSrc, auth);
		this.posts = posts;

		if (isComeFromMenu) {
			for (Post post : posts) {
				this.category = post.getCategory().getLowerString();
			}
		}
		setNames();
	}

	private void setNames() {
		if (Objects.isNull(this.category)) {
			setTitle(getMsgUtil().getMessage("post.title"));
		} else {
			setTitle(getMsgUtil().getMessage(CategoryType.valueOf(this.category.toUpperCase()).getMessageCode()));
		}
		number = getMsgUtil().getMessage("post.list.number");
		postTitle = getMsgUtil().getMessage("post.list.postTitle");
		createdUser = getMsgUtil().getMessage("post.list.createdUser");
		createdDate = getMsgUtil().getMessage("post.list.createdDate");
		write = getMsgUtil().getMessage("post.list.write");
		btnMessageSend = getMsgUtil().getMessage("post.list.note.send");
	}

	private List<Post> posts;
	private String category;
	private String number;
	private String postTitle;
	private String createdUser;
	private String createdDate;
	private String write;
	private String btnMessageSend;
}
