package itu.p16.gestion.finance.repository;

import itu.p16.gestion.finance.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Integer> {
    List<SousCategorie> findByCategorieId(Integer categorieId);
}
