package com.FitHealthy.Fit_Healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FitHealthy.Fit_Healthy.entity.Role;

/**
 * Interface Repository pour l'entité {@link Role}.
 * Cette interface permet de gérer les opérations sur les rôles dans la base de données.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Recherche un rôle par son nom.
     * 
     * @param name Le nom du rôle à rechercher.
     * @return Un objet {@link Role} si un rôle avec ce nom est trouvé, sinon {@code null}.
     */
    Role findByName(String name);
}
