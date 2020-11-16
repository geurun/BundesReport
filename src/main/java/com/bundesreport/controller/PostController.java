package com.bundesreport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.dto.PostForm;
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
			model = createLayout(model, (User) authentication.getPrincipal());
		} else {
			model = createLayout(model, null);
		}
		model.addAttribute("posts", postService.findPostsByCategory(CategoryType.valueOf(categoryName.toUpperCase())));
		return "posts/postList";
	}

	@GetMapping(value = "/posts/new")
	public String createForm(Model model, Authentication authentication) {
		model = createLayout(model, (User) authentication.getPrincipal());
		model.addAttribute("postForm", new PostForm());
		model.addAttribute("categories", CategoryType.values());
		return "posts/createPostForm";
	}

	@PostMapping(value = "/posts/new")
	public String create(PostForm form, Authentication authentication) {
		form.setUser((User) authentication.getPrincipal());
		postService.createPost(form);
		return "redirect:/";
	}

	@GetMapping(value = "/posts/detail/{postId}")
	public String detail(@PathVariable("postId") Long postId, Model model, Authentication authentication) {
		if (authentication != null) {
			model = createLayout(model, (User) authentication.getPrincipal());
		} else {
			model = createLayout(model, null);
		}
		model.addAttribute("post", postService.findPost(postId));
		return "posts/postDetail";
	}

	@GetMapping(value = "/posts/update/{postId}")
	public String updateForm(@PathVariable("postId") Long postId, Model model, Authentication authentication) {
		model = createLayout(model, (User) authentication.getPrincipal());
		Post post = postService.findPost(postId);
		model.addAttribute("postForm", post.toPostForm());
		model.addAttribute("post", post);
		model.addAttribute("categories", CategoryType.values());
		return "posts/updatePostForm";
	}

	@PostMapping(value = "/posts/update/{postId}")
	public String update(@PathVariable("postId") Long postId, PostForm form, Authentication authentication,
			Model model) {
		model = createLayout(model, (User) authentication.getPrincipal());
		postService.updatePost(form, postId);
		return "redirect:/posts/detail/" + postId;
	}

	@GetMapping(value = "/posts/delete/{postId}")
	public String delete(@PathVariable("postId") Long postId, Authentication authentication, Model model) {
		model = createLayout(model, (User) authentication.getPrincipal());
		postService.deletePost(postId);
		return "redirect:/";
	}

}