package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userName;

	private String hashedPassword;

	private String email;

	@CreationTimestamp
	private LocalDateTime createdDate;

	private boolean deleted;

	@OneToMany(mappedBy = "user")
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "sender")
	private List<Note> sendNotes = new ArrayList<>();

	@OneToMany(mappedBy = "receiver")
	private List<Note> receiveNotes = new ArrayList<>();

}
