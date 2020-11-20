package com.bundesreport.domain;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "file")
public class File {

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private Blob content;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	// ==relational method==//
	public void setPost(Post post) {
		this.post = post;
		post.getFiles().add(this);
	}

}
