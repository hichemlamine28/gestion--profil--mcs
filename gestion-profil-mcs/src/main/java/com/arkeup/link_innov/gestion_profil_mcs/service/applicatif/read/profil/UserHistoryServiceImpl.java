package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserHistoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.UserHistoryMongoRepository;


@Service
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
	private UserHistoryMongoRepository userHistoryRepository;
	

	// Create operation
	@Override
	public UserHistory create(String actionDate, List<UserHistoryActions> actions) {

		return userHistoryRepository.save(new UserHistory(actionDate, actions));
	}

	// Retrieve operation
	@Override
	public List<UserHistory> getAll() {
		return userHistoryRepository.findAll();
	}

	@Override
	public List<UserHistory> getAllByDate(String actionDate) {
		return userHistoryRepository.findByactionDate(actionDate);
	}

//	@Override
//	public UserHistory getByFirstName(String firstName) {
//		return personRepository.findByFirstName(firstName);
//	}

	// Update operation
//	@Override
//	public UserHistory update(String firstName, String lastName, int age) {
//		UserHistory p = personRepository.findByFirstName(firstName);
//		p.setLastName(lastName);
//		p.setAge(age);
//		return personRepository.save(p);
//	}

	// Delete operation
//	@Override
//	public void deleteAll() {
//		personRepository.deleteAll();
//	}

//	@Override
//	public void delete(String firstName) {
//		UserHistory p = personRepository.findByFirstName(firstName);
//		personRepository.delete(p);
//	}
}