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
	private String userName;
	private String hashedPassword;
	private String email;
	private LocalDateTime createdDate;
	@Enumerated(EnumType.STRING)
	private LanguageStatus languageStatus; // [KO, DE]
	private boolean deleted;

	public User toEntity() {
		return User.builder().id(id).userName(userName)
				.hashedPassword(new BCryptPasswordEncoder().encode(hashedPassword)).email(email)
				.languageStatus(languageStatus).deleted(deleted).build();
	}
	
	@Builder
	public UserForm(Long id, String userName, String hashedPassword, String email, LanguageStatus languageStatus,
			boolean deleted) {
		this.id = id;
		this.userName = userName;
		this.hashedPassword = hashedPassword;
		this.email = email;
		this.languageStatus = languageStatus;
		this.deleted = deleted;
	}
}
