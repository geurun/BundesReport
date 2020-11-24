package com.bundesreport.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bundesreport.config.AjaxResponse;
import com.bundesreport.domain.Comment;
import com.bundesreport.domain.User;
import com.bundesreport.dto.CommentForm;
import com.bundesreport.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController extends PageController {

	@Autowired
	private final CommentService commentService;

	@PostMapping(value = "/comment/write")
	public String write(CommentForm form, Authentication auth) {
		if (Objects.nonNull(auth)) {
			form.setUser((User) auth.getPrincipal());
		}
		if (Objects.nonNull(form.getId())) {
			Optional<Comment> commentWrapper = commentService.findById(form.getId());
			commentService.update(commentWrapper.get().getUpdateModel(form));
			return "redirect:/post/view/" + commentWrapper.get().getPost().getId();
		}
		commentService.save(form);
		return "redirect:/post/view/" + form.getPost().getId();
	}

	@PostMapping(value = "/comment/update")
	public ResponseEntity<?> updateViaAjax(@RequestBody CommentForm form, Authentication auth) {
		AjaxResponse result = new AjaxResponse();
		if (Objects.nonNull(form.getId())) {
			Optional<Comment> commentWrapper = commentService.findById(form.getId());
			commentService.update(commentWrapper.get().getUpdateModel(form));
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/comment/delete/{commentId}")
	public String delete(@PathVariable("commentId") Long commentId) {
		return "redirect:/post/view/" + commentService.delete(commentId);
	}

}
