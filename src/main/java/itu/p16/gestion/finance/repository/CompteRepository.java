package itu.p16.gestion.finance.repository;

import itu.p16.gestion.finance.model.Compte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
    List<Compte> findByPosteId(Integer posteId); 
}
