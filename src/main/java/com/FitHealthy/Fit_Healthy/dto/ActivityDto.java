package com.FitHealthy.Fit_Healthy.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

/**
 * Représente un DTO (Data Transfer Object) pour l'entité Activité.
 * Ce DTO contient les informations d'une activité, telles que son nom et les listes associées aux préventions et disciplines liées à l'activité.
 * Il est utilisé pour le transfert de données entre la couche de présentation et la couche de service ou de stockage.
 */
public class ActivityDto {

    /**
     * L'identifiant unique de l'activité.
     * Cet identifiant est utilisé pour distinguer une activité des autres dans le système.
     */
    private Long id;

    /**
     * Le nom de l'activité.
     * Ce champ ne doit pas être vide. Un message d'erreur est généré si la contrainte de non-vidité est violée.
     */
    @NotEmpty(message = "Le nom ne doit pas être vide")
    private String name;

    /**
     * La liste des DTOs de prévention associés à cette activité.
     * Une activité peut avoir plusieurs mesures de prévention associées.
     */
    private List<PreventionDto> prevs;

    /**
     * La liste des DTOs de disciplines associés à cette activité.
     * Une activité peut être associée à plusieurs disciplines.
     */
    private List<DisciplineDto> discips;

    /**
     * Récupère l'identifiant de l'activité.
     * 
     * @return l'identifiant de l'activité.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'activité.
     * 
     * @param id l'identifiant de l'activité.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom de l'activité.
     * 
     * @return le nom de l'activité.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'activité.
     * 
     * @param name le nom de l'activité à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère la liste des DTOs de prévention associés à cette activité.
     * 
     * @return la liste des DTOs de prévention.
     */
    public List<PreventionDto> getPrevs() {
        return prevs;
    }

    /**
     * Définit la liste des DTOs de prévention associés à cette activité.
     * 
     * @param prevs la liste des DTOs de prévention à définir.
     */
    public void setPrevs(List<PreventionDto> prevs) {
        this.prevs = prevs;
    }

    /**
     * Récupère la liste des DTOs de disciplines associés à cette activité.
     * 
     * @return la liste des DTOs de disciplines.
     */
    public List<DisciplineDto> getDiscips() {
        return discips;
    }

    /**
     * Définit la liste des DTOs de disciplines associés à cette activité.
     * 
     * @param discips la liste des DTOs de disciplines à définir.
     */
    public void setDiscips(List<DisciplineDto> discips) {
        this.discips = discips;
    }

}