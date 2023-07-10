package com.exciting.index.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exciting.dto.AmusementAimageDTO;
import com.exciting.entities.AmusementEntity;

public interface IndexRepository extends JpaRepository<AmusementEntity, Integer>, CustomIndexRepository{
}
