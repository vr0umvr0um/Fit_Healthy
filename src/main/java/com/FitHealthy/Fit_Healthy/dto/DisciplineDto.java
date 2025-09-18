package com.FitHealthy.Fit_Healthy.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * Représente un DTO (Data Transfer Object) pour l'entité Discipline.
 * Ce DTO contient les informations d'une discipline, telles que son nom.
 * Il est utilisé pour le transfert de données entre la couche de présentation et la couche de service ou de stockage.
 */
public class DisciplineDto {

    /**
     * L'identifiant unique de la discipline.
     * Cet identifiant est utilisé pour distinguer une discipline des autres dans le système.
     */
    private Long id;

    /**
     * Le nom de la discipline.
     * Ce champ ne doit pas être vide. Un message d'erreur est généré si la contrainte de non-vidité est violée.
     */
    @NotEmpty(message = "Le nom ne doit pas être vide")
    private String name;

    /**
     * Récupère l'identifiant de la discipline.
     * 
     * @return l'identifiant de la discipline.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la discipline.
     * 
     * @param id l'identifiant de la discipline.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom de la discipline.
     * 
     * @return le nom de la discipline.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la discipline.
     * 
     * @param name le nom de la discipline à définir.
     */
    public void setName(String name) {
        this.name = name;
    }
}