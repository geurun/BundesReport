package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = -1457804012376126489L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userName;

	private String hashedPassword;

	private String email;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Enumerated(EnumType.STRING)
	private LanguageStatus languageStatus; // [KO, DE]

	private boolean deleted;

	@OneToMany(mappedBy = "user")
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sender")
	private List<Note> sendNotes = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver")
	private List<Note> receiveNotes = new ArrayList<>();

	@Builder
	public User(Long id, String userName, String hashedPassword, String email, LanguageStatus languageStatus,
			boolean deleted) {
		this.id = id;
		this.userName = userName;
		this.hashedPassword = hashedPassword;
		this.email = email;
		this.languageStatus = languageStatus;
		this.deleted = deleted;
	}

	public UserForm toUserForm() {
		return UserForm.builder().id(id).userName(userName).hashedPassword(hashedPassword).email(email)
				.languageStatus(languageStatus).deleted(deleted).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// ToDo: Administrator or User
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority("USER"));
		return roles;
	}

	@Override
	public String getPassword() {
		return this.hashedPassword;
	}

	@Override
	public String getUsername() {
		return this.userName;
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
