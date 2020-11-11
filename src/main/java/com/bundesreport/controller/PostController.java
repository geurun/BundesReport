package com.bundesreport.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bundesreport.domain.Post;
import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@GetMapping(value = "/posts/{categoryName}")
	public String list(@PathVariable("categoryName") CategoryType categoryName, Model model) {
		int categoryId = categoryName.getId();
		List<Post> posts = postService.findPostsByCategory(categoryId);
		model.addAttribute("posts", posts);
		return "posts/postList";
	}
}
