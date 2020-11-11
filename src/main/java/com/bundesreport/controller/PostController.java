package com.bundesreport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController extends PageController {

	private final PostService postService;

	@GetMapping(value = "/posts/{categoryName}")
	public String list(@PathVariable("categoryName") CategoryType categoryName, Model model,
			Authentication authentication) {
		if (authentication != null) {
			// ToDo: Data get with User
			// model = createLayout(model, (User) authentication.getPrincipal());
			model.addAttribute("posts", postService.findPostsByCategory(categoryName.getId()));
			return "posts/postList";
		}
		// model = createLayout(model, null);
		model.addAttribute("posts", postService.findPostsByCategory(categoryName.getId()));
		return "posts/postList";
	}
}
