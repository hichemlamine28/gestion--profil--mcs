package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.Date;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;

public interface UserHistoryService {

	public UserHistory create(Date actionDate, List<UserHistoryActions> actions);

	List<UserHistory> getAll();

//	UserHistory getByFirstName(String firstName);

//	UserHistory update(String firstName, String lastName, int age);

//	void deleteAll();

//	void delete(String firstName);
}
