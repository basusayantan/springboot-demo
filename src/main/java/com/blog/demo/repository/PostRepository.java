package com.blog.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.demo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	Optional<Post> findByTitle(String title);
	Page<Post> findAllByUsername(String username, Pageable pageable);

}