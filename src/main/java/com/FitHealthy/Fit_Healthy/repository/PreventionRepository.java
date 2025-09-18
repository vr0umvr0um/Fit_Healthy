package com.FitHealthy.Fit_Healthy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FitHealthy.Fit_Healthy.entity.Prevention;

/**
 * Interface Repository pour la gestion des entités {@code Prevention}.
 * Cette interface hérite de {@link JpaRepository}, ce qui lui permet de fournir des
 * fonctionnalités de base pour la manipulation des données en base, telles que la création,
 * la mise à jour, la suppression et la récupération des objets {@code Prevention}.
 */
public interface PreventionRepository extends JpaRepository<Prevention, Long> {

    /**
     * Récupère toutes les préventions enregistrées en base de données.
     * 
     * @return une liste contenant toutes les instances de {@code Prevention}.
     */
    List<Prevention> findAll();
}
