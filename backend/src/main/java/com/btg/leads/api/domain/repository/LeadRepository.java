package com.btg.leads.api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btg.leads.api.domain.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    void save(Optional<Lead> lead);

}
