package com.bundesreport.type;

import lombok.Getter;

@Getter
public enum CategoryType {
	FREE_BOARD(0), ABROAD_BOARD(1), LIVING_QA(2), FLEA_MARKET(3), JOB_SEARCH(4), CLUB(5), EVENT_NOTIFICATION(6),
	RECIPE(7), GALLERY(8);

	CategoryType(int value) {
		this.value = value;
	}

	private int value;

	public String getMessageCode() {
		if (FREE_BOARD.getValue() == this.value) {
			return "post.title.freeBoard";
		}
		if (ABROAD_BOARD.getValue() == this.value) {
			return "post.title.abroadBoard";
		}
		if (LIVING_QA.getValue() == this.value) {
			return "post.title.livingQA";
		}
		if (FLEA_MARKET.getValue() == this.value) {
			return "post.title.fleaMarket";
		}
		if (JOB_SEARCH.getValue() == this.value) {
			return "post.title.jobSearch";
		}
		if (CLUB.getValue() == this.value) {
			return "post.title.club";
		}
		if (EVENT_NOTIFICATION.getValue() == this.value) {
			return "post.title.eventNotification";
		}
		return "post.title.recipe";
	}

	public String getLowerString() {
		if (FREE_BOARD.getValue() == this.value) {
			return "free_board";
		}
		if (ABROAD_BOARD.getValue() == this.value) {
			return "abroad_board";
		}
		if (LIVING_QA.getValue() == this.value) {
			return "living_qa";
		}
		if (FLEA_MARKET.getValue() == this.value) {
			return "flea_market";
		}
		if (JOB_SEARCH.getValue() == this.value) {
			return "job_search";
		}
		if (CLUB.getValue() == this.value) {
			return "club";
		}
		if (EVENT_NOTIFICATION.getValue() == this.value) {
			return "event_notification";
		}
		return "recipe";
	}
}
