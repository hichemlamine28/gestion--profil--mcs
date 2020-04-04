package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "userHistoryActions")
public class UserHistoryActions {

	@Id
	private String action_id;
	private String action_Name;
	private Map<String, Integer> userId = new HashMap<>();
//	private int occurence = 1;

	public UserHistoryActions() {
	}

	public UserHistoryActions(String action_id, String action_Name, Map<String, Integer> userId) {
		super();
		this.action_id = action_id;
		this.action_Name = action_Name;
		if (userId == null) {
			userId = new HashMap<>();
			userId.put("non", 0);

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

	public Map<String, Integer> getUserId() {
		return userId;
	}

	public void setUserId(Map<String, Integer> userId) {
		this.userId = userId;
	}

//	public int getOccurence() {
//		return occurence;
//	}
//
//	public void setOccurence(int occurence) {
//		this.occurence = occurence;
//	}

}
