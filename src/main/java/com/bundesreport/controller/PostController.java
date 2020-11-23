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

import com.bundesreport.component.CommentListBean;
import com.bundesreport.component.PostBean;
import com.bundesreport.component.PostListBean;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.dto.CommentForm;
import com.bundesreport.dto.PostForm;
import com.bundesreport.dto.SearchForm;
import com.bundesreport.service.PostLikeService;
import com.bundesreport.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController extends PageController {

	@Autowired
	private final PostService postService;

	@Autowired
	private final PostLikeService postLikeService;

	@GetMapping(value = "/post/list/{category}")
	public String list(@PathVariable("category") String category, Model model, Authentication auth) {
		try {
			model = createLayout(model, auth);
			if ((category.equals("GALLERY")) || (category.equals("gallery"))) {
				model.addAttribute("bean",
						new PostListBean(msgSrc, auth, postService.findByCategory(category), category, true));
				return "post/gallery";
			}
			model.addAttribute("bean",
					new PostListBean(msgSrc, auth, postService.findByCategory(category), category, true));
			return "post/list";
		} catch (Exception e) {
			return "redirect:/404";
		}
	}

	@PostMapping(value = "/post/search")
	public String search(SearchForm form, Model model, Authentication auth) {
		try {
			model = createLayout(model, auth);
			model.addAttribute("bean",
					new PostListBean(msgSrc, auth, postService.findByTitleOrContent(form.getKey()), null, false));
			return "post/list";
		} catch (Exception e) {
			return "redirect:/404";
		}
	}

	@GetMapping(value = "/post/write/{category}")
	public String write(@PathVariable("category") String category, Model model, Authentication auth) {
		try {
			model = createLayout(model, auth);
			User user = null;
			if (Objects.nonNull(auth)) {
				user = (User) auth.getPrincipal();
			}
			model.addAttribute("bean", new PostBean(msgSrc, auth, null, category));
			model.addAttribute("postForm", new PostForm(category, user));
			return "post/write";
		} catch (Exception e) {
			return "redirect:/404";
		}
	}

	@PostMapping(value = "/post/write")
	public String write(PostForm form, Authentication auth) {
		if (Objects.nonNull(auth)) {
			form.setUser((User) auth.getPrincipal());
		}
		if (Objects.nonNull(form.getId())) {
			Optional<Post> postWrapper = postService.findById(form.getId());
			postService.update(postWrapper.get().getUpdateModel(form));
			return "redirect:/post/list/" + form.getCategory();
		}
		postService.save(form);
		return "redirect:/post/list/" + form.getCategory();
	}

	@GetMapping(value = "/post/view/{postId}")
	public String view(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		model = createLayout(model, auth);
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		PostBean bean = new PostBean(msgSrc, auth, post.get(), null);
		bean.setHasLike(postLikeService.countByPostAndUser(post.get(), user));
		model.addAttribute("bean", bean);
		model.addAttribute("commentForm", new CommentForm(user, post.get()));
		model.addAttribute("commentListBean", new CommentListBean(msgSrc, auth, post.get(), post.get().getComments()));
		return "post/view";
	}

	@GetMapping(value = "/post/update/{postId}")
	public String update(@PathVariable("postId") Long postId, Model model, Authentication auth) {
		model = createLayout(model, auth);
		Optional<Post> post = postService.findById(postId);
		if (Objects.isNull(post)) {
			return "redirect:/";
		}
		model.addAttribute("bean", new PostBean(msgSrc, auth, post.get(), null));
		model.addAttribute("postForm", post.get().toPostForm());
		return "post/write";
	}

	@GetMapping(value = "/post/delete/{postId}")
	public String delete(@PathVariable("postId") Long postId) {
		return "redirect:/post/list/" + postService.delete(postId);
	}

}
