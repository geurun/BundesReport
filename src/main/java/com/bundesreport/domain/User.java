package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bundesreport.dto.UserForm;
import com.bundesreport.type.LanguageStatus;

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
@Table(name = "USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = -1457804012376126489L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean deleted;

	private String username;

	private String password;

	private String email;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Enumerated(EnumType.STRING)
	private LanguageStatus languageStatus; // [KO, DE]

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<PostLike> postLikes = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<CommentLike> commentLikes = new ArrayList<>();

	@OneToMany(mappedBy = "sender")
	private List<Note> sendNotes = new ArrayList<>();

	@OneToMany(mappedBy = "receiver", cascade = { CascadeType.ALL })
	private List<Note> receiveNotes = new ArrayList<>();

	@Builder
	public User(Long id, boolean deleted, String username, String password, String email, LocalDateTime createdDate,
			LanguageStatus languageStatus) {
		this.id = id;
		this.deleted = deleted;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdDate = createdDate;
		this.languageStatus = languageStatus;
	}

	public UserForm toUserForm() {
		return UserForm.builder().id(id).deleted(deleted).username(username).password(password).email(email)
				.createdDate(createdDate).languageStatus(languageStatus).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority("USER"));
		return roles;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.deleted;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.deleted;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.deleted;
	}

	@Override
	public boolean isEnabled() {
		return !this.deleted;
	}
}
