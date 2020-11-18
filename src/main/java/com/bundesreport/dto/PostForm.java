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
	private String title;
	private String content;
	private CategoryType category;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private boolean deleted;
	private User user;

	public PostForm(CategoryType category, User user) {
		this.category = category;
		this.user = user;
	}

	public Post toEntity() {
		return Post.builder().id(id).title(title).content(content).category(category).deleted(deleted).user(user)
				.build();
	}

	@Builder
	public PostForm(Long id, String title, String content, CategoryType category, boolean deleted, User user) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.deleted = deleted;
		this.user = user;
	}
}
