package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostForm {
	private Long id;
	private boolean deleted;
	private String title;
	private String content;
	private CategoryType category;
	private LocalDateTime createdDate;
	private User user;
	private int viewCount;

	public PostForm(String category, User user) {
		this.category = CategoryType.valueOf(category.toUpperCase());
		this.user = user;
	}

	public Post toEntity() {
		return Post.builder().id(id).deleted(deleted).title(title).content(content).category(category)
				.createdDate(createdDate).user(user).viewCount(viewCount).build();
	}

	@Builder
	public PostForm(Long id, boolean deleted, String title, String content, CategoryType category,
			LocalDateTime createdDate, User user, int viewCount) {
		this.id = id;
		this.deleted = deleted;
		this.title = title;
		this.content = content;
		this.category = category;
		this.createdDate = createdDate;
		this.user = user;
		this.viewCount = viewCount;
	}
}
