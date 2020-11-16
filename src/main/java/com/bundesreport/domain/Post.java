package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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

import com.bundesreport.dto.PostForm;
import com.bundesreport.type.CategoryType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "POSTS")
public class Post {

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String title;

	@Lob
	private String content;

	private CategoryType category;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	private int good;

	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<File> files = new ArrayList<>();

	@Builder
	public Post(Long id, String title, String content, CategoryType category, User user, boolean deleted) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.user = user;
		this.deleted = deleted;

		// this.createdDate = LocalDateTime.now();
	}

	public Post() {

	}

	// ==relational method==//
	public void setUser(User user) {
		this.user = user;
		user.getPosts().add(this);
	}

	public PostForm toPostForm() {
		return PostForm.builder().id(id).title(title).content(content).category(category).deleted(deleted).user(user)
				.build();
	}

}
