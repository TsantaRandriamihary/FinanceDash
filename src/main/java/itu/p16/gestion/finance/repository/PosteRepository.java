package itu.p16.gestion.finance.repository;

import itu.p16.gestion.finance.model.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Integer> {
    List<Poste> findBySousCategorieId(Integer sousCategorieId); 
    Optional<Poste> findById(Integer id); 

}
