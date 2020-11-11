package com.bundesreport.type;

public enum CategoryType {
	FREE_BOARD(1),
	ABROAD_BOARD(2),
	LIVING_QA(3),
	FLEA_MARKET(4),
	JOB_SEARCH(5),
	CLUB(6),
	EVENT_NOTIFICATION(7),
	RECIPE(8),
	GALLERY(9);

	private int id;

	CategoryType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
