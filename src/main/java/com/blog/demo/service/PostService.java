package com.blog.demo.service;

import com.blog.demo.payload.PostDto;
import com.blog.demo.payload.PostPagedDto;

public interface PostService {
	
	PostDto createPost(PostDto dto);
	PostPagedDto getPostsByUser(String username, int pageNo, int pageSize);
	PostDto getPostByTitle(String title);
	PostDto updatePost(String content, String title);
	void deletePost(String title);
	PostDto likePost(long id);

}