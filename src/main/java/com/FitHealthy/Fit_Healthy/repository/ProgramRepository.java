package com.FitHealthy.Fit_Healthy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FitHealthy.Fit_Healthy.entity.Program;

/**
 * Interface pour l'accès aux données des programmes.
 * Cette interface étend JpaRepository pour bénéficier des opérations CRUD sur l'entité Program.
 */
public interface ProgramRepository extends JpaRepository<Program, Long> {
    
    /**
     * Récupère la liste des programmes associés à un utilisateur spécifié par son identifiant.
     * 
     * @param userId l'identifiant de l'utilisateur
     * @return une liste de programmes liés à cet utilisateur
     */
    List<Program> findByUserId(Long userId);
    
    /**
     * Récupère la liste des programmes associés à une activité spécifiée par son identifiant.
     * 
     * @param activityId l'identifiant de l'activité
     * @return une liste de programmes liés à cette activité
     */
    List<Program> findByActivityId(Long activityId);
}
