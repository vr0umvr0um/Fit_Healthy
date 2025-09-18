package com.FitHealthy.Fit_Healthy.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.FitHealthy.Fit_Healthy.entity.Activity;

/**
 * Interface de gestion des activités dans la base de données.
 * Cette interface étend JpaRepository pour fournir des opérations CRUD standard.
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * Récupère toutes les activités enregistrées en base de données.
     *
     * @return une liste contenant toutes les instances de {@code Activity}.
     *         Si aucune activité n'est trouvée, une liste vide est retournée.
     */
    List<Activity> findAll();
}
