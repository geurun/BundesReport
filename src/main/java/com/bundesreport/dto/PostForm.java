package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;

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
	private boolean deleted;
	private User user;

	public Post toEntity() {
		return Post.builder().id(id).title(title).content(content).category(category).deleted(deleted).user(user)
				.build();
	}
}
