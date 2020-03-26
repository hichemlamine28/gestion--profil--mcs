package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.SkillESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.SkillMongoRepository;

@Repository
public class SkillRepositoryImpl extends CommonMongoToESRepositoryImpl<Skill, String, SkillMongoRepository, SkillESRepository> implements SkillRepository {
	
	@Override
	public Optional<Skill> getSkill(String id) {
		return this.mongoRepository.findById(id);
	}

	@Override
	public Page<Skill> listSkill(String userId, Pageable pageable) {
		return this.mongoRepository.findByUserId(userId, pageable);
	}

	@Override
	public Page<Skill> findSkill(String name, Pageable pageable) {
		return this.mongoRepository.findByNameContaining(name, pageable);
	}
}
