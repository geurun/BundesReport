package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean deleted;

	private String title;

	@Lob
	private String content;

	private CategoryType category;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade = { CascadeType.ALL })
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = { CascadeType.ALL })
	private List<PostLike> likes = new ArrayList<>();

	private int viewCount;

	@Builder
	public Post(Long id, boolean deleted, String title, String content, CategoryType category,
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

	public PostForm toPostForm() {
		return PostForm.builder().id(id).deleted(deleted).title(title).content(content).category(category)
				.createdDate(createdDate).user(user).viewCount(viewCount).build();
	}

	public Post getUpdateModel(PostForm form) {
		setTitle(form.getTitle());
		setContent(form.getContent());
		return this;
	}

	public int getLikesCount() {
		return getLikes().size();
	}

	public String getFirstImage() {
		String target = "<img src=";
		int targetNum = getContent().indexOf(target);
		if (targetNum == -1) {
			return "<img src=\"/img/no-image.png\">";
		}
		return getContent().substring(targetNum, ((getContent().substring(targetNum).indexOf(">") + 1) + targetNum));
	}
}
