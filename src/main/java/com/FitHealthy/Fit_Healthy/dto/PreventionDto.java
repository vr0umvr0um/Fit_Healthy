package com.FitHealthy.Fit_Healthy.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * Représente un DTO (Data Transfer Object) pour l'entité Prévention.
 * Ce DTO contient les informations d'une prévention, telles que son nom.
 * Il est utilisé pour le transfert de données entre la couche de présentation et la couche de service ou de stockage.
 */
public class PreventionDto {

    /**
     * L'identifiant unique de la prévention.
     * Cet identifiant est utilisé pour distinguer une prévention des autres dans le système.
     */
    private Long id;

    /**
     * Le nom de la prévention.
     * Ce champ ne doit pas être vide. Un message d'erreur est généré si la contrainte de non-vidité est violée.
     */
    @NotEmpty(message = "Le nom ne doit pas être vide")
    private String name;

    /**
     * Récupère l'identifiant de la prévention.
     * 
     * @return l'identifiant de la prévention.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la prévention.
     * 
     * @param id l'identifiant de la prévention.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom de la prévention.
     * 
     * @return le nom de la prévention.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la prévention.
     * 
     * @param name le nom de la prévention à définir.
     */
    public void setName(String name) {
        this.name = name;
    }
}