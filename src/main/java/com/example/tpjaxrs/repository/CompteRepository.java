package com.example.tpjaxrs.repository;

import com.example.tpjaxrs.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
