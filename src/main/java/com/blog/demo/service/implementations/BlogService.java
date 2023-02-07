package com.blog.demo.service.implementations;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.demo.exceptions.blogexceptions.BlogException;
import com.blog.demo.exceptions.blogexceptions.DuplicatePostException;
import com.blog.demo.exceptions.blogexceptions.ResourceNotFoundException;
import com.blog.demo.model.Comment;
import com.blog.demo.model.Post;
import com.blog.demo.payload.CommentDto;
import com.blog.demo.payload.CommentPagedDto;
import com.blog.demo.payload.PostDto;
import com.blog.demo.payload.PostPagedDto;
import com.blog.demo.repository.CommentRepository;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.service.CommentService;
import com.blog.demo.service.PostService;
import com.blog.demo.service.utils.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService implements PostService, CommentService {

	private final PostRepository posts;
	private final CommentRepository comments;
	private final Mapper mapper;
	
	private Post getPost(long id) {
		return posts.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post", "ID", Long.toString(id)));
	}
	
	private Post getPost(String title) {
		return posts.findByTitle(title)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Title", title));
	}
		
	private Comment getComment(long id) {
		return comments.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "ID", Long.toString(id)));
	}
	
	@Override
	public PostDto createPost(PostDto dto) {
		Post post = mapper.mapToEntity(dto);
		try {
			post = posts.save(post);
		} catch(DataIntegrityViolationException e) {
			throw new DuplicatePostException("Post", "Title", post.getTitle());
		}
		return mapper.mapToDto(post);
	}
	
	@Override
	public PostPagedDto getPostsByUser(String username, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Post> postsPage = posts.findAllByUsername(username, pageable);
		return PostPagedDto.builder()
				.post(postsPage.getContent().stream()
						.map(mapper::mapToDto).collect(Collectors.toList()))
				.username(username)
				.pageNo(postsPage.getNumber())
				.totalPages(postsPage.getTotalPages())
				.totalPosts(postsPage.getTotalElements())
				.build();
	}
	
	@Override
	public PostDto getPostByTitle(String title) {
		Post post = getPost(title);
		return mapper.mapToDto(post);
	}
	
	@Override
	public PostDto likePost(long id) {
		Post post = getPost(id);
		post.setLikes(post.getLikes() + 1);
		post = posts.save(post);
		return mapper.mapToDto(post);
	}
	
	@Override
	public PostDto updatePost(String content, String title) {
		Post post = getPost(title);
		if(content.equalsIgnoreCase(post.getContent())) {
			throw new BlogException("Updated content does not differ from the original");
		}
		post.setContent(content);
		post = posts.save(post);
		return mapper.mapToDto(post);
	}
	
	@Override
	public void deletePost(String title) {
		Post post = getPost(title);
		posts.delete(post);
	}

	@Override
	public CommentDto createComment(long postid, CommentDto dto) {
		Comment comment = mapper.mapToEntity(dto);
		Post post = getPost(postid);
		comment.setPost(post);
		comment = comments.save(comment);
		return mapper.mapToDto(comment);
	}
	
	@Override
	public CommentPagedDto getAllComments(long postid, int pageNo, int pageSize) {
		Post post = getPost(postid);
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Comment> commentsPage = comments.findAllByPost(post, pageable);
		return CommentPagedDto.builder()
				.post(mapper.mapToDto(post))
				.comments(commentsPage.getContent().stream()
							.map(mapper::mapToDto).collect(Collectors.toList()))
				.pageNo(commentsPage.getNumber())
				.totalPages(commentsPage.getTotalPages())
				.totalComments(commentsPage.getTotalElements())
				.build();
		}

	@Override
	public CommentDto updateComment(long postid, long id, String content) {
		Post post = getPost(postid);
		Comment comment = getComment(id);
		if(!post.equals(comment.getPost()))
			throw new BlogException("Invalid Post:Comment pair");
		comment.setContent(content);
		comment = comments.save(comment);
		return mapper.mapToDto(comment);
	}

	@Override
	public void deleteComment(long postid, long id) {
		Post post = getPost(postid);
		Comment comment = getComment(id);
		if(!post.equals(comment.getPost()))
			throw new BlogException("Invalid Post:Comment pair");
		comments.delete(comment);
	}

}