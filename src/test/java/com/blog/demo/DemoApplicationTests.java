package com.blog.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.blog.demo.service.CommentService;
import com.blog.demo.service.utils.Mapper;

@SpringBootTest
class DemoApplicationTests {
	
	private ApplicationContext context;
	
	public DemoApplicationTests(ApplicationContext context) {
		this.context = context;
	}

	@BeforeEach
	public void init(TestInfo info) {
		System.out.format("\nPerforming: %s\n", info.getTestMethod().get().getName());
	}
	
	@Test
	public void contextLoads() {
		assertNotNull(context.getBean(Mapper.class));
		assertNotNull(context.getBean(CommentService.class));
	}
	
}