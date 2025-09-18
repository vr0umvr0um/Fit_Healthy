package com.FitHealthy.Fit_Healthy.service;

import java.util.List;
import com.FitHealthy.Fit_Healthy.dto.PreventionDto;

/**
 * Service pour accéder aux informations de prévention.
 */
public interface PreventionService {

    /**
     * Récupère toutes les informations de prévention disponibles.
     * 
     * @return Une liste d'objets PreventionDto contenant toutes les préventions.
     */
    List<PreventionDto> findAllPreventions();
}
