package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserHistoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryServiceImpl;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/userHistory")
public class UserHistoryController {

	@Autowired
	private UserHistoryServiceImpl personService;

//	@RequestMapping("/create")
//	public String create(@RequestParam String actionDate, @RequestParam List<UserHistoryActions> actions) {
//		UserHistory p = personService.create(actionDate, actions);
//		return p.toString();
//	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/getAll")
	public List<UserHistoryDTO> getAll() {
		List<UserHistory> histories = personService.getAll();

		return userHistoryToDTO(histories);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/findByDate")
	public List<UserHistoryDTO> findByDate(
			@ApiParam(name = "RecommandationDTO", value = "{\"skillId\":\"uid\", \"username\":\"username\"}", required = true) @RequestBody UserHistoryDTO userHistoryDTO) {

		List<UserHistory> histories = personService.getAllByDate(userHistoryDTO.getActionDate());

		return userHistoryToDTO(histories);
	}

//	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
//	@GetMapping("/findByToday")
//	public List<UserHistoryDTO> findToday() {
//
//		String pattern = "dd-MM-yyyy";
//		DateFormat df = new SimpleDateFormat(pattern);
//		String todayAsString = df.format(new Date());
//		List<UserHistory> histories = personService.getAllByDate(todayAsString);
//
//		return userHistoryToDTO(histories);
//	}

	private List<UserHistoryDTO> userHistoryToDTO(List<UserHistory> histories) {
		List<UserHistoryDTO> userHistoryDTOs = new ArrayList<>();
		for (UserHistory userHistory : histories) {
			UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
			userHistoryDTO.setActionDate(userHistory.getActionDate());
			userHistoryDTO.setUserHistoryActions(userHistory.getActions());
			userHistoryDTOs.add(userHistoryDTO);
		}
		return userHistoryDTOs;
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