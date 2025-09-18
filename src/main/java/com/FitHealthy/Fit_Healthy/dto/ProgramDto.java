package com.FitHealthy.Fit_Healthy.dto;

/**
 * Représente un DTO (Data Transfer Object) pour l'entité Programme.
 * Ce DTO contient les informations relatives à un programme utilisateur, telles que l'activité associée,
 * la note donnée à l'activité et la note moyenne.
 * Il est utilisé pour le transfert de données entre la couche de présentation et la couche de service ou de stockage.
 */
public class ProgramDto {

    /**
     * L'identifiant unique du programme.
     * Cet identifiant est utilisé pour distinguer un programme des autres dans le système.
     */
    private Long id;

    /**
     * L'identifiant de l'utilisateur associé à ce programme.
     * Ce champ permet de lier le programme à un utilisateur spécifique.
     */
    private Long userId;

    /**
     * L'identifiant de l'activité associée à ce programme.
     * Ce champ permet de lier le programme à une activité spécifique.
     */
    private Long activityId;

    /**
     * Le nom de l'activité associée à ce programme.
     * Ce champ contient le nom de l'activité que l'utilisateur a choisie.
     */
    private String activityName;

    /**
     * La note donnée par l'utilisateur pour cette activité dans le cadre du programme.
     * Ce champ représente la note donnée par l'utilisateur à l'activité.
     */
    private int rating;

    /**
     * La note moyenne de l'activité associée à ce programme.
     * Ce champ représente la moyenne des notes données à cette activité par tous les utilisateurs.
     */
    private double averageRating;

    /**
     * Récupère l'identifiant du programme.
     * 
     * @return l'identifiant du programme.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du programme.
     * 
     * @param id l'identifiant du programme.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère l'identifiant de l'utilisateur associé au programme.
     * 
     * @return l'identifiant de l'utilisateur.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Définit l'identifiant de l'utilisateur associé au programme.
     * 
     * @param userId l'identifiant de l'utilisateur.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Récupère l'identifiant de l'activité associée à ce programme.
     * 
     * @return l'identifiant de l'activité.
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * Définit l'identifiant de l'activité associée au programme.
     * 
     * @param activityId l'identifiant de l'activité.
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * Récupère le nom de l'activité associée au programme.
     * 
     * @return le nom de l'activité.
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Définit le nom de l'activité associée au programme.
     * 
     * @param activityName le nom de l'activité.
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Récupère la note donnée par l'utilisateur pour cette activité.
     * 
     * @return la note donnée par l'utilisateur.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Définit la note donnée par l'utilisateur pour cette activité.
     * 
     * @param rating la note à définir.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Récupère la note moyenne de l'activité associée à ce programme.
     * 
     * @return la note moyenne de l'activité.
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Définit la note moyenne de l'activité associée à ce programme.
     * 
     * @param averageRating la note moyenne à définir.
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}