
package com.blog.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="POSTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"TITLE"})})
public class Post {
	
	@Id @Column(name="POST_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="TITLE", nullable=false)
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="LIKES", columnDefinition="INTEGER(10) DEFAULT 0")
	private int likes;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments;
	
	public Post(String title, String content, String username) {
		this.title = title;
		this.content = content;
		this.username = username;
		this.comments = new ArrayList<>();
	}
	
	@Override
	public boolean equals(Object that) {
		if(this == that) {
			return true;
		}
		if(this.getTitle().equals(((Post)that).getTitle())) {
			return true;
		}
		return false;
	}

}