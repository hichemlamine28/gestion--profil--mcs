package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
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
	@Override
	public UserHistory update(String actionDate, List<UserHistoryActions> actions) {
		List<UserHistory> p = userHistoryRepository.findByactionDate(actionDate);
		UserHistory history = p.get(0);
		history.setActions(actions);
		return userHistoryRepository.save(history);
	}
@Override
	public void addOrUbdateHistory(String userID, String actionName) {
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String historyDate = df.format(today);
		List<String> userIDs = new ArrayList<>();
		userIDs.add(userID);

		List<UserHistory> historiesAll = getAllByDate(historyDate);
		if (historiesAll.isEmpty() || historiesAll == null) {

			UserHistoryActions action = new UserHistoryActions("2", actionName, userIDs);
			List<UserHistoryActions> actions = new ArrayList<>();
			actions.add(action);
			create(historyDate, actions);
		} else {
			UserHistory existingHistory = historiesAll.get(0);
			List<UserHistoryActions> actions = existingHistory.getActions();
			for (UserHistoryActions userHistoryActions : actions) {
				if (userHistoryActions.getAction_Name().equals(actionName)) {
					System.out.println("Action exist add user");
					List<String> ExistinguserIDs = new ArrayList<>();
					int newOccurence = userHistoryActions.getOccurence()+1;
					ExistinguserIDs = userHistoryActions.getUserId();
					ExistinguserIDs.addAll(userIDs);
					userHistoryActions.setUserId(ExistinguserIDs);
					userHistoryActions.setOccurence(newOccurence);
					update(historyDate, actions);
				} else {
					System.out.println("New Action");
					UserHistoryActions newAction = new UserHistoryActions();
					newAction.setAction_Name(actionName);
					List<String> newserIDs = new ArrayList<>();
					newserIDs.addAll(userIDs);
					newAction.setUserId(newserIDs);
					actions.add(newAction);
					update(historyDate, actions);
				}
			}
		}
	}
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