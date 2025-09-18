package com.FitHealthy.Fit_Healthy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitHealthy.Fit_Healthy.dto.ActivityDto;
import com.FitHealthy.Fit_Healthy.dto.ProgramDto;
import com.FitHealthy.Fit_Healthy.entity.Program;
import com.FitHealthy.Fit_Healthy.repository.ProgramRepository;
import com.FitHealthy.Fit_Healthy.service.ActivityService;
import com.FitHealthy.Fit_Healthy.service.ProgramService;

/**
 * Implémentation du service ProgramService.
 * Fournit des méthodes pour gérer les programmes d'activités, notamment l'ajout, la mise à jour et le calcul des évaluations moyennes.
 */
@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepo;
    @Autowired
    private ActivityService activityServ;

    /**
     * Ajoute un programme d'activité pour un utilisateur donné.
     * 
     * @param userId L'identifiant de l'utilisateur.
     * @param activityId L'identifiant de l'activité.
     * @param rating L'évaluation de l'activité pour l'utilisateur.
     */
    @Override
    public void addProgram(Long userId, Long activityId, int rating) {
        Program program = new Program();
        program.setUserId(userId);
        program.setActivityId(activityId);
        program.setRating(rating);
        programRepo.save(program);
    }

    /**
     * Met à jour la note d'un programme existant.
     * 
     * @param programId L'identifiant du programme à mettre à jour.
     * @param rating La nouvelle note à attribuer au programme.
     * @throws IllegalArgumentException Si le programme avec l'ID spécifié n'existe pas.
     */
    @Override
    public void updateProgramRating(Long programId, int rating) {
        Program program = programRepo.findById(programId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid program Id:" + programId));
        program.setRating(rating);
        programRepo.save(program);
    }

    /**
     * Calcule la note moyenne des programmes associés à un utilisateur donné.
     * 
     * @param userId L'identifiant de l'utilisateur pour lequel la note moyenne est calculée.
     * @return La note moyenne des programmes de l'utilisateur, ou null si l'utilisateur n'a aucun programme.
     */
    @Override
    public Double calculateAverageRatingByUserId(Long userId) {
        List<Program> programs = programRepo.findByUserId(userId);
        if (programs.isEmpty()) {
            return null; // Retourne null si aucun programme n'existe
        }
        double sum = programs.stream()
                .mapToDouble(Program::getRating)
                .sum();
        return sum / programs.size();
    }

    /**
     * Récupère tous les programmes associés à un utilisateur donné.
     * 
     * @param userId L'identifiant de l'utilisateur pour lequel les programmes sont récupérés.
     * @return Une liste de DTO de programmes.
     */
    @Override
    public List<ProgramDto> findAllProgramsByUserId(Long userId) {
        List<Program> programs = programRepo.findByUserId(userId);
        return programs.stream()
                .map(program -> {  // Utilisation d'une lambda pour plus de clarté
                    ProgramDto programDto = mapToProgramDto(program);
                    ActivityDto activityDto = activityServ.findActivityById(program.getActivityId());
                    programDto.setActivityName(activityDto != null ? activityDto.getName() : "Activité inconnue"); // Gestion des nulls
                    return programDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Convertit une entité Program en un DTO ProgramDto.
     * 
     * @param program L'entité Program à convertir.
     * @return Le DTO correspondant à l'entité Program.
     */
    private ProgramDto mapToProgramDto(Program program) {
        ProgramDto programDto = new ProgramDto();
        programDto.setId(program.getId());
        programDto.setUserId(program.getUserId());
        programDto.setActivityId(program.getActivityId());
        programDto.setRating(program.getRating());
        return programDto; 
    }
}
