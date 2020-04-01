package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryServiceImpl;

@RestController
@RequestMapping(value = "/profilss")
public class UserHistoryController {

	@Autowired
	private UserHistoryServiceImpl personService;

	@RequestMapping("/create")
	public String create(@RequestParam Date actionDate, @RequestParam List<UserHistoryActions> actions) {
		UserHistory p = personService.create(actionDate, actions);
		return p.toString();
	}

//	@RequestMapping("/get")
//	public UserHistory getPerson(@RequestParam String firstName) {
//		return personService.getByFirstName(firstName);
//	}

	@RequestMapping("/getAll")
	public List<UserHistory> getAll() {
		return personService.getAll();
	}

//	@RequestMapping("/update")
//	public String update(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
//		UserHistory p = personService.update(firstName, lastName, age);
//		return p.toString();
//	}

//	@RequestMapping("/delete")
//	public String delete(@RequestParam String firstName) {
//		personService.delete(firstName);
//		return "Deleted " + firstName;
//	}

//	@RequestMapping("/deleteAll")
//	public String deleteAll() {
//		personService.deleteAll();
//		return "Deleted all records";
//	}

}