package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.favorite.FavoriteRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.OtherProductionRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.PatentRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProjectRepository;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

@Service
public class ProductionRSMImpl implements ProductionRSM {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private OtherProductionRepository otherProductionRepository;

    @Autowired
    private PatentRepository patentRepository;

    @Autowired
    private FavoriteRSM favoriteRSM;

    @Override
    public Page<Productions> getByOwnerId(String ownerId, Pageable pageable, String order) {
        List<Productions> result = new ArrayList<Productions>();

        result.addAll(projectRepository.findByOwnerId(ownerId));
        result.addAll(otherProductionRepository.findByOwnerId(ownerId));
        result.addAll(patentRepository.findByOwnerId(ownerId));

        switch (order) {
            case "ASC":
                result = result.stream()
                        .sorted(Comparator.comparing(Productions::getCreationDate))
                        .collect(Collectors.toList());
                break;
            case "DESC":
                result = result.stream()
                        .sorted(Comparator.comparing(Productions::getCreationDate).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                result = result.stream()
                        .sorted(Comparator.comparing(Productions::getCreationDate))
                        .collect(Collectors.toList());
        }

        Page<Productions> resultPage = new PageImpl<Productions>(result, pageable, result.size());
        return resultPage;
    }

    @Override
    public List<Productions> findAllByOwnerId(String ownerId) {
        List<Productions> result = new ArrayList<Productions>();
        result.addAll(patentRepository.findByOwnerId(ownerId));
        result.addAll(projectRepository.findByOwnerId(ownerId));
        result.addAll(otherProductionRepository.findByOwnerId(ownerId));
        return result;
    }

    @Override
    public List<Productions> findAll() {
        List<Productions> result = new ArrayList<Productions>();
        result.addAll(patentRepository.findAll());
        result.addAll(projectRepository.findAll());
        result.addAll(otherProductionRepository.findAll());
        return result;
    }

    @Override
    public Page<Productions> getByIds(List<String> productionIds, Pageable pageable) {
        List<Productions> result = new ArrayList<Productions>();
        result.addAll(projectRepository.findAllByIdIn(productionIds));
        result.addAll(otherProductionRepository.findAllByIdIn(productionIds));
        result.addAll(patentRepository.findAllByIdIn(productionIds));
        Page<Productions> resultPage = new PageImpl<Productions>(result, pageable, result.size());
        return resultPage;
    }

    @Override
    public Productions findById(String id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            return projectOptional.get();
        }

        Optional<OtherProduction> otherProductionOptional = otherProductionRepository.findById(id);
        if (otherProductionOptional.isPresent()) {
            return otherProductionOptional.get();
        }

        Optional<Patent> patentOptional = patentRepository.findById(id);
        if (patentOptional.isPresent()) {
            return patentOptional.get();
        }

        return null;
    }

    @Override
    public Page<Productions> getPaginatedProductions(List<String> productionIds, String filter, String categorie, Pageable pageable) {
        List<Productions> result = new ArrayList<>();
        categorie = StringUtils.isEmpty(categorie) ? "all" : categorie;
        switch (categorie.toLowerCase()) {
            case "project":
                Page<Project> project = projectRepository.getPaginatedProjects(productionIds, filter, pageable);
                result.addAll(project.getContent());
                break;
            case "other production":
                Page<OtherProduction> otherProduction = otherProductionRepository.getPaginatedOtherProductions(productionIds, filter, pageable);
                result.addAll(otherProduction.getContent());
                break;
            case "patent":
                Page<Patent> patent = patentRepository.getPaginatedPatent(productionIds, filter, pageable);
                result.addAll(patent.getContent());
                break;
            default:
                result.addAll(projectRepository.findAllByIdIn(productionIds));
                result.addAll(otherProductionRepository.findAllByIdIn(productionIds));
                result.addAll(patentRepository.findAllByIdIn(productionIds));

                if (pageable.getSort().toString().contains("DESC")) {
                    result.sort(Comparator.comparing(Productions::getTitle).reversed());
                } else {
                    result.sort(Comparator.comparing(Productions::getTitle));
                }

                int start = (int) pageable.getOffset();
                int end = (start + pageable.getPageSize()) > productionIds.size() ? productionIds.size() : (start + pageable.getPageSize());
                PageImpl<Productions> pageResult = new PageImpl<>(result.subList(start, end), pageable, productionIds.size());
                return pageResult;
        }

        Page<Productions> resultPage = new PageImpl<>(result, pageable, result.size());
        return resultPage;
    }

}
