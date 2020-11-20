package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.bundesreport.dto.PostForm;
import com.bundesreport.type.CategoryType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Where(clause = "deleted = false")
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private boolean deleted;

	private String title;

	@Lob
	private String content;

	private CategoryType category;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<Like> likes = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<File> files = new ArrayList<>();

	@Builder
	public Post(Long id, boolean deleted, String title, String content, CategoryType category, User user) {
		this.id = id;
		this.deleted = deleted;
		this.title = title;
		this.content = content;
		this.category = category;
		this.user = user;
	}

	public PostForm toPostForm() {
		return PostForm.builder().id(id).deleted(deleted).title(title).content(content).category(category).user(user)
				.build();
	}

	public Post getUpdateModel(PostForm form) {
		setTitle(form.getTitle());
		setContent(form.getContent());
		return this;
	}
}
