package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class UserHistoryActionsDTO extends BaseDTO {

	@JsonInclude
	private String action_id;
	@JsonInclude
	private String actionName;
	@JsonInclude
	private List<String> userId;

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getAction_Name() {
		return actionName;
	}

	public void setAction_Name(String action_Name) {
		this.actionName = action_Name;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

}
