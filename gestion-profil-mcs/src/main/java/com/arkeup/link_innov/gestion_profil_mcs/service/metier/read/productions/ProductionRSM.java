package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;

public interface ProductionRSM {

    public Page<Productions> getByOwnerId(String ownerId, Pageable pageable, String order);

    public List<Productions> findAllByOwnerId(String ownerId);

    public List<Productions> findAll();

    public Page<Productions> getByIds(List<String> productionIds, Pageable pageable);

    public Productions findById(String id);

    public Page<Productions> getPaginatedProductions(List<String> productionIds, String filter, String categorie, Pageable pageable);
}
