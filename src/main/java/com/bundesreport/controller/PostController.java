package com.bundesreport.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.component.PostBean;
import com.bundesreport.component.PostListBean;
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
	public String list(@PathVariable("categoryName") String categoryName, Model model, Authentication auth) {
		try {
			CategoryType category = CategoryType.valueOf(categoryName.toUpperCase());
			User user = null;
			if (Objects.nonNull(auth)) {
				user = (User) auth.getPrincipal();
			}
			model = createLayout(model, user);
			model.addAttribute("bean",
					new PostListBean(messageSource, user, postService.findByCategory(category), category));
			return "posts/list";
		} catch (IllegalArgumentException e) {
			return "redirect:/404";
		}
	}

	@GetMapping(value = "/posts/new/{categoryName}")
	public String write(@PathVariable("categoryName") String categoryName, Model model, Authentication auth) {
		CategoryType category = CategoryType.valueOf(categoryName.toUpperCase());
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		model = createLayout(model, user);
		model.addAttribute("bean", new PostBean(messageSource, user, null, category));
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("postForm", new PostForm(category, user));
		return "posts/write";
	}

	@PostMapping(value = "/posts/new")
	public String write(PostForm form, Authentication auth) {
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
			form.setUser(user);
		}
		if (Objects.nonNull(form.getId())) {
			Optional<Post> post = postService.findById(form.getId());
			Post updPost = post.get();
			postService.update(updPost.getUpdateModel(updPost, form));
			return "redirect:/posts/" + form.getCategory();
		}
		postService.save(form);
		return "redirect:/posts/" + form.getCategory();
	}

	@GetMapping(value = "/posts/view/{postId}")
	public String view(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		model = createLayout(model, user);
		model.addAttribute("bean", new PostBean(messageSource, user, post.get(), null));
		return "posts/view";
	}

	@GetMapping(value = "/posts/update/{postId}")
	public String update(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		model = createLayout(model, user);
		model.addAttribute("bean", new PostBean(messageSource, user, post.get(), null));
		model.addAttribute("categoryName", post.get().getCategory().toString());
		model.addAttribute("postForm", post.get().toPostForm());
		return "posts/write";
	}

	@GetMapping(value = "/posts/delete/{postId}")
	public String delete(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		return "redirect:/posts/" + postService.delete(postId);
	}
}
