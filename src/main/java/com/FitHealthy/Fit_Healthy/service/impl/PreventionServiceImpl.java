package com.FitHealthy.Fit_Healthy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitHealthy.Fit_Healthy.dto.PreventionDto;
import com.FitHealthy.Fit_Healthy.entity.Prevention;
import com.FitHealthy.Fit_Healthy.repository.PreventionRepository;
import com.FitHealthy.Fit_Healthy.service.PreventionService;

/**
 * Implémentation du service PreventionService.
 * Fournit des méthodes pour gérer les préventions.
 */
@Service
public class PreventionServiceImpl implements PreventionService {

    @Autowired
    private PreventionRepository preventionRepo;

    /**
     * Récupère toutes les préventions.
     * 
     * @return Une liste de DTO de préventions.
     */
    @Override
    public List<PreventionDto> findAllPreventions() {
        List<Prevention> prevs = preventionRepo.findAll();
        return prevs.stream()
                .map(this::mapToPreventionDto)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une entité Prevention en un DTO PreventionDto.
     * 
     * @param prevention L'entité Prevention à convertir.
     * @return Le DTO correspondant à l'entité Prevention.
     */
    private PreventionDto mapToPreventionDto(Prevention prevention) {
        PreventionDto preventionDto = new PreventionDto();
        preventionDto.setId(prevention.getId());
        preventionDto.setName(prevention.getName());
        return preventionDto;
    }
}