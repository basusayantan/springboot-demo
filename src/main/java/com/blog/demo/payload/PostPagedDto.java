package com.blog.demo.payload;

import java.util.List;

import com.blog.demo.service.utils.builders.PostPagedDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostPagedDto {
	
	private List<PostDto> posts;
	private String username;
	private int pageNo;
	private int totalPages;
	private long totalPosts;
	
	public static PostPagedDtoBuilder builder() {
		return new PostPagedDtoBuilder();
	}

}