package com.FitHealthy.Fit_Healthy.service;

import java.util.List;

import com.FitHealthy.Fit_Healthy.dto.ProgramDto;

/**
 * Service pour gérer les programmes d'activités des utilisateurs.
 */
public interface ProgramService {

    /**
     * Ajoute un programme pour un utilisateur spécifique lié à une activité.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @param activityId L'identifiant de l'activité.
     * @param rating L'évaluation initiale du programme.
     */
    void addProgram(Long userId, Long activityId, int rating);
    
    /**
     * Récupère tous les programmes associés à un utilisateur.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return Une liste d'objets ProgramDto représentant les programmes de l'utilisateur.
     */
    List<ProgramDto> findAllProgramsByUserId(Long userId);
    
    /**
     * Met à jour l'évaluation d'un programme spécifique.
     *
     * @param programId L'identifiant du programme à mettre à jour.
     * @param rating La nouvelle évaluation du programme.
     */
    void updateProgramRating(Long programId, int rating);
    
    /**
     * Calcule la moyenne des évaluations pour un utilisateur donné.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return La note moyenne des programmes associés à l'utilisateur.
     */
    Double calculateAverageRatingByUserId(Long userId);
}
