package com.bundesreport.dto;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bundesreport.domain.User;
import com.bundesreport.type.LanguageStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserForm {
	private Long id;
	private boolean deleted;
	private String username;
	private String password;
	private String email;
	private LocalDateTime createdDate;
	@Enumerated(EnumType.STRING)
	private LanguageStatus languageStatus; // [KO, DE]

	public User toEntity() {
		return User.builder().id(id).deleted(deleted).username(username)
				.password(new BCryptPasswordEncoder().encode(password)).email(email).createdDate(createdDate)
				.languageStatus(languageStatus).build();
	}

	@Builder
	public UserForm(Long id, boolean deleted, String username, String password, String email, LocalDateTime createdDate,
			LanguageStatus languageStatus) {
		this.id = id;
		this.deleted = deleted;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdDate = createdDate;
		this.languageStatus = languageStatus;
	}
}
