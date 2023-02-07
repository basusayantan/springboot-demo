package com.blog.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@Entity
@Table(name="COMMENTS")
public class Comment {
	
	@Id @Column(name="COMMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="USERNAME")
	private String username;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="POST_ID", nullable=false)
	private Post post;
	
	public Comment(String content, String username) {
		this.content = content;
		this.username = username;
	}

}