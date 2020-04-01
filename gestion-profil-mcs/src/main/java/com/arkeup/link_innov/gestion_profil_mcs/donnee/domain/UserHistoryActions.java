package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.List;

public class UserHistoryActions {

	private String UserId;
	private List<String> actions;

	public UserHistoryActions(String userId, List<String> actions) {
		super();
		UserId = userId;
		this.actions = actions;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

}
