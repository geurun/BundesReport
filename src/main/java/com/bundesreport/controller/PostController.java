package com.bundesreport.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.dto.PostForm;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.PostService;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController extends PageController {

	private final PostService postService;

	@GetMapping(value = "/posts/{categoryName}")
	public String list(@PathVariable("categoryName") String categoryName, Model model, Authentication authentication) {
		if (authentication != null) {
			model = createLayout(model, (User)authentication.getPrincipal());
		}
		else {
			model = createLayout(model, null);
		}
		CategoryType categoryType = CategoryType.valueOf(categoryName.toUpperCase());
		List<Post> posts = postService.findPostsByCategory(categoryType);
		model.addAttribute("posts", posts);
		return "posts/postList";
	}

	@GetMapping(value = "/posts/new")
	public String createForm(Model model, Authentication authentication) {
		model = createLayout(model, (User) authentication.getPrincipal());
		model.addAttribute("postForm", new PostForm((User) authentication.getPrincipal()));
		model.addAttribute("categories", CategoryType.values());
		return "posts/createPostForm";
	}

	@PostMapping(value = "/posts/new")
	public String create(PostForm form) {
		postService.createPost(form);
		return "redirect:/";
	}
}
