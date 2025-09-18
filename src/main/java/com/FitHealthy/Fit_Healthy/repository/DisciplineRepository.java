package com.FitHealthy.Fit_Healthy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FitHealthy.Fit_Healthy.entity.Discipline;

/**
 * Interface pour l'accès aux données des disciplines. 
 * Cette interface étend JpaRepository pour bénéficier des opérations CRUD sur l'entité Discipline.
 */
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    /**
     * Récupère la liste de toutes les disciplines présentes dans la base de données.
     * 
     * @return une liste de toutes les disciplines
     */
    List<Discipline> findAll();
}