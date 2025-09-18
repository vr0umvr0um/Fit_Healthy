package com.FitHealthy.Fit_Healthy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitHealthy.Fit_Healthy.dto.ActivityDto;
import com.FitHealthy.Fit_Healthy.dto.DisciplineDto;
import com.FitHealthy.Fit_Healthy.dto.PreventionDto;
import com.FitHealthy.Fit_Healthy.entity.Activity;
import com.FitHealthy.Fit_Healthy.entity.Discipline;
import com.FitHealthy.Fit_Healthy.entity.Prevention;
import com.FitHealthy.Fit_Healthy.repository.ActivityRepository;
import com.FitHealthy.Fit_Healthy.service.ActivityService;

/**
 * Implémentation du service ActivityService.
 * Fournit des méthodes pour gérer les activités et les associer aux préventions et disciplines.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepo;

    /**
     * Récupère toutes les activités, avec leurs préventions et disciplines associées.
     * 
     * @return Une liste de DTO d'activités.
     */
    @Override
    public List<ActivityDto> getAllActivitiesWithPreventionsAndDisciplines() {
        List<Activity> acts = activityRepo.findAll();
        return acts.stream()
                .map(this::mapToActivityDto)
                .collect(Collectors.toList());
    }

    /**
     * Recherche des activités en fonction d'une pathologie et d'une discipline.
     * 
     * @param pathology La pathologie à filtrer (peut être null ou vide).
     * @param discipline La discipline à filtrer (peut être null ou vide).
     * @return Une liste de DTO d'activités correspondant aux critères de recherche.
     */
    @Override
    public List<ActivityDto> searchActivities(String pathology, String discipline) {
        return activityRepo.findAll().stream()
                .filter(activity -> 
                    (pathology == null || pathology.isBlank() || activity.getPrevs().stream()
                        .anyMatch(p -> p.getName().equalsIgnoreCase(pathology)))
                    && 
                    (discipline == null || discipline.isBlank() || activity.getDiscips().stream()
                        .anyMatch(d -> d.getName().equalsIgnoreCase(discipline)))
                )
                .map(this::mapToActivityDto)
                .collect(Collectors.toList());
    }

    /**
     * Recherche une activité par son identifiant.
     * 
     * @param id L'identifiant de l'activité à rechercher.
     * @return Le DTO de l'activité si trouvée, sinon null.
     */
    @Override
    public ActivityDto findActivityById(Long id) {
        return activityRepo.findById(id).map(this::mapToActivityDto).orElse(null);
    }

    /**
     * Convertit une entité Activity en un DTO ActivityDto.
     * 
     * @param activity L'entité Activity à convertir.
     * @return Le DTO correspondant à l'entité Activity.
     */
    private ActivityDto mapToActivityDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setName(activity.getName());

        // Mapper les préventions
        List<PreventionDto> preventionDtos = activity.getPrevs().stream()
                .map(this::mapToPreventionDto)
                .collect(Collectors.toList());
        activityDto.setPrevs(preventionDtos);

        // Mapper les disciplines
        List<DisciplineDto> disciplineDtos = activity.getDiscips().stream()
                .map(this::mapToDisciplineDto)
                .collect(Collectors.toList());
        activityDto.setDiscips(disciplineDtos);

        return activityDto;
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

    /**
     * Convertit une entité Discipline en un DTO DisciplineDto.
     * 
     * @param discipline L'entité Discipline à convertir.
     * @return Le DTO correspondant à l'entité Discipline.
     */
    private DisciplineDto mapToDisciplineDto(Discipline discipline) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        return disciplineDto;
    }
}