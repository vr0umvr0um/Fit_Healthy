package com.FitHealthy.Fit_Healthy.service;

import java.util.List;
import com.FitHealthy.Fit_Healthy.dto.ActivityDto;

/**
 * Service pour gérer les activités.
 */
public interface ActivityService {

    /**
     * Récupère toutes les activités, incluant les informations de prévention et disciplines associées.
     * 
     * @return Une liste d'objets ActivityDto contenant toutes les activités.
     */
    List<ActivityDto> getAllActivitiesWithPreventionsAndDisciplines();

    /**
     * Recherche des activités en fonction d'une pathologie et/ou d'une discipline.
     * 
     * @param pathology La pathologie associée à l'activité.
     * @param discipline La discipline associée à l'activité.
     * @return Une liste d'objets ActivityDto correspondant aux critères spécifiés.
     */
    List<ActivityDto> searchActivities(String pathology, String discipline);

    /**
     * Trouve une activité par son identifiant unique.
     * 
     * @param Id L'identifiant unique de l'activité.
     * @return L'objet ActivityDto correspondant à l'identifiant, ou null si non trouvé.
     */
    ActivityDto findActivityById(Long Id);
}
