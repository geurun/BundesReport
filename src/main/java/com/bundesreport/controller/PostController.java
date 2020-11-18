package com.bundesreport.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController extends PageController {

	@Autowired
	private final PostService postService;

	@GetMapping(value = "/posts/{category}")
	public String list(@PathVariable("category") String category, Model model, Authentication auth) {
		try {
			model = createLayout(model, auth);
			User user = null;
			if (Objects.nonNull(auth)) {
				user = (User) auth.getPrincipal();
			}
			model.addAttribute("bean", new PostListBean(msgSrc, user, postService.findByCategory(category), category));
			return "posts/list";
		} catch (Exception e) {
			return "redirect:/404";
		}
	}

	@GetMapping(value = "/posts/new/{category}")
	public String write(@PathVariable("category") String category, Model model, Authentication auth) {
		try {
			model = createLayout(model, auth);
			User user = null;
			if (Objects.nonNull(auth)) {
				user = (User) auth.getPrincipal();
			}
			model.addAttribute("bean", new PostBean(msgSrc, user, null, category));
			model.addAttribute("postForm", new PostForm(category, user));
			model.addAttribute("categoryName", category);
			return "posts/write";
		} catch (Exception e) {
			return "redirect:/404";
		}
	}

	@PostMapping(value = "/posts/new")
	public String write(PostForm form, Authentication auth) {
		if (Objects.nonNull(auth)) {
			form.setUser((User) auth.getPrincipal());
		}
		if (Objects.nonNull(form.getId())) {
			Optional<Post> postWrapper = postService.findById(form.getId());
			postService.update(postWrapper.get().getUpdateModel(form));
			return "redirect:/posts/" + form.getCategory();
		}
		postService.save(form);
		return "redirect:/posts/" + form.getCategory();
	}

	@GetMapping(value = "/posts/view/{postId}")
	public String view(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		model = createLayout(model, auth);
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		model.addAttribute("bean", new PostBean(msgSrc, user, post.get(), null));
		return "posts/view";
	}

	@GetMapping(value = "/posts/update/{postId}")
	public String update(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		model = createLayout(model, auth);
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		model.addAttribute("bean", new PostBean(msgSrc, user, post.get(), null));
		model.addAttribute("categoryName", post.get().getCategory().toString());
		model.addAttribute("postForm", post.get().toPostForm());
		return "posts/write";
	}

	@GetMapping(value = "/posts/delete/{postId}")
	public String delete(@PathVariable("postId") Long postId) {
		return "redirect:/posts/" + postService.delete(postId);
	}
}
