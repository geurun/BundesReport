package com.bundesreport;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
	
	@Test
	public void 포스트_모두_조회() throws Exception {
		//given
		Post post2 = new Post();
		Post post3 = new Post();
		post2.setTitle("제목2");
		post2.setContent("내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다");
		post3.setTitle("제목3");
		post3.setContent("내용3입니다내용3입니다내용3입니다내용3입니다내용3입니다내용3입니다");
		postRepository.save(post2);
		postRepository.save(post3);

		
		//when
		List<Post> postsAll = postService.findPosts();
		
		//then
		int i=0;
		for (Post post : postsAll) {
			i++;
			System.out.println("" + post.getId() + post.getTitle());
		}
		assertEquals(2, i);
	}
}
