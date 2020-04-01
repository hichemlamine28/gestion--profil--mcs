package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "UserHistory")
public class UserHistory extends BaseDTO {
	@Id
	String id;
	Date actionDate;
	List<UserHistoryActions> userHistoryActions;
//	List<String> actions;
//	String firstName;
//	String lastName;
//	int age;

	public UserHistory(Date actionDate, List<UserHistoryActions> actions) {
		this.actionDate = actionDate;
		this.userHistoryActions = actions;
//		this.age = age;

	}

//	public String getFirstName() {
//		return firstName;
//	}

//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}

//	public String getLastName() {
//		return lastName;
//	}

//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

//	public int getAge() {
//		return age;
//	}

//	public void setAge(int age) {
//		this.age = age;
//	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public List<UserHistoryActions> getActions() {
		return userHistoryActions;
	}

	public void setActions(List<UserHistoryActions> actions) {
		this.userHistoryActions = actions;
	}

	public String toString() {
		return "Action Date:" + actionDate + " User actions:" + userHistoryActions.toString();
	}
}