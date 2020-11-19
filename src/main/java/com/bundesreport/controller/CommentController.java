package com.bundesreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bundesreport.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {

	@Autowired
	private final CommentService commentService;

	@GetMapping(value = "/comment/delete/{commentId}")
	public String delete(@PathVariable("commentId") Long commentId) {
		return "redirect:/post/view/" + commentService.delete(commentId);
	}
}
