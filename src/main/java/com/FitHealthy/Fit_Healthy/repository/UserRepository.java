package com.FitHealthy.Fit_Healthy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FitHealthy.Fit_Healthy.entity.User;

/**
 * Interface pour accéder aux données de l'entité User.
 * Fournit des méthodes de manipulation de la base de données.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Recherche un utilisateur par son adresse email.
     *
     * @param email L'adresse email de l'utilisateur à rechercher.
     * @return Un objet User correspondant à l'adresse email spécifiée, 
     *         ou null si aucun utilisateur n'est trouvé.
     */
    User findByEmail(String email);
}
