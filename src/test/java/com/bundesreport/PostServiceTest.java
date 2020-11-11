package com.bundesreport;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
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

	@Autowired
	PostService postService;
	@Autowired
	PostRepository postRepository;

	@Test
	public void 포스트생성() throws Exception {
		// given
		Post post = new Post();
		post.setTitle("제목1");
		post.setContent("내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다내용1입니다");

		// when
		Long postId = postService.savePost(post);

		// then
		assertEquals(post, postRepository.findOne(postId));
		assertEquals("제목1", postRepository.findOne(postId).getTitle());

	}

	@Test
	public void 포스트_모두_조회() throws Exception {
		// given
		Post post2 = new Post();
		Post post3 = new Post();
		post2.setTitle("제목2");
		post2.setContent("내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다내용2입니다");
		post3.setTitle("제목3");
		post3.setContent("내용3입니다내용3입니다내용3입니다내용3입니다내용3입니다내용3입니다");
		postRepository.save(post2);
		postRepository.save(post3);

		// when
		List<Post> postsAll = postService.findPosts();

		// then
		int i = 0;
		for (Post post : postsAll) {
			i++;
			System.out.println("" + post.getId() + post.getTitle());
		}
		assertEquals(2, i);
	}

	@Test
	public void 카테고리별_포스트_조회() throws Exception {
		// given
		Post post1 = new Post();
		post1.setTitle("title1");
		post1.setContent("content1");
		post1.setCategory(1);
		Post post2 = new Post();
		post2.setTitle("title2");
		post2.setContent("content2");
		post2.setCategory(1);
		Post post3 = new Post();
		post3.setTitle("title3");
		post3.setContent("content3");
		post3.setCategory(2);
		postService.savePost(post1);
		postService.savePost(post2);
		postService.savePost(post3);

		// when
		List<Post> postList1 = postService.findPostsByCategory(1);
		List<Post> postList2 = postService.findPostsByCategory(2);

		// then
		int i1 = 0, i2 = 0;
		for (Post post : postList1) {
			i1++;
		}
		for (Post post : postList2) {
			i2++;
		}
		assertEquals(2, i1);
		assertEquals(1, i2);
	}
}
