package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/getAll")
	public List<UserHistoryDTO> getAll() {
		List<UserHistory> histories = personService.getAll();

		return userHistoryToDTO(histories);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/findByDate")
	public List<UserHistoryDTO> findByDate(
			@ApiParam(name = "RecommandationDTO", value = "{\"actionDate\":\"uid\"}", required = true) @RequestBody UserHistoryDTO userHistoryDTO) {

		List<UserHistory> histories = personService.getAllByDate(userHistoryDTO.getActionDate());

		return userHistoryToDTO(histories);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/isFirstConnection")
	public boolean isFirstConnection() {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		List<UserHistory> histories = personService.getAll();
		for (UserHistory userHistory : histories) {
			for (UserHistoryActions actions : userHistory.getActions()) {
				if (actions.getUserId().containsKey(userName)) {
					return false;
				}
			}
		}

		return true;
	}

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

}