package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "userHistoryActions")
public class UserHistoryActions {

	@Id
	private String action_id;
	private String action_Name;
	private List<String> userId;
	private int occurence = 1;

	public UserHistoryActions() {
	}

	public UserHistoryActions(String action_id, String action_Name, List<String> userId) {
		super();
		this.action_id = action_id;
		this.action_Name = action_Name;
		if (userId == null) {
			userId = new ArrayList<>();
			userId.add("non");

		}
		this.userId = userId;
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getAction_Name() {
		return action_Name;
	}

	public void setAction_Name(String action_Name) {
		this.action_Name = action_Name;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

}
