package com.bundesreport;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bundesreport.domain.Post;
import com.bundesreport.repository.PostRepository;
import com.bundesreport.service.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {
	
	@Autowired PostService postService;
	@Autowired PostRepository postRepository;
	
	@Test
	public void 포스트생성() throws Exception {
		//given
		Post post = new Post();
		post.setTitle("제목1");
		post.setContent("내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다");

		//when
		Long postId = postService.savePost(post);
		
		//then
		assertEquals(post, postRepository.findOne(postId));
		assertEquals("제목1", postRepository.findOne(postId).getTitle());
	}
}
