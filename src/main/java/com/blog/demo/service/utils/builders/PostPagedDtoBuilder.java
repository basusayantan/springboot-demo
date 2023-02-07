package com.blog.demo.service.utils.builders;

import java.util.List;

import com.blog.demo.payload.PostDto;
import com.blog.demo.payload.PostPagedDto;

public class PostPagedDtoBuilder {
	
	private List<PostDto> posts;
	private String username;
	private int pageNo;
	private int totalPages;
	private long totalPosts;
	
	public PostPagedDtoBuilder post(List<PostDto> posts) {
		this.posts = posts;
		return this;
	}
	
	public PostPagedDtoBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public PostPagedDtoBuilder pageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
	
	public PostPagedDtoBuilder totalPages(int totalPages) {
		this.totalPages = totalPages;
		return this;
	}
	
	public PostPagedDtoBuilder totalPosts(long totalPosts) {
		this.totalPosts = totalPosts;
		return this;
	}
	
	public PostPagedDto build() {
		return new PostPagedDto(posts, username, pageNo, totalPages, totalPosts);
	}

}