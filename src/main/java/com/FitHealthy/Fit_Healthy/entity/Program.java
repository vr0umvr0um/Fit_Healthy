package com.FitHealthy.Fit_Healthy.entity;

import jakarta.persistence.*;

/**
 * Représente un programme dans le système, qui associe un utilisateur à une activité et permet d'évaluer ce programme avec une note.
 * Cette classe est mappée à la table "programs" dans la base de données.
 */
@Entity
@Table(name = "programs")
public class Program {

    /**
     * Identifiant unique du programme.
     * Ce champ est généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identifiant de l'utilisateur associé au programme.
     */
    @Column
    private Long userId;

    /**
     * Identifiant de l'activité associée au programme.
     */
    @Column
    private Long activityId;

    /**
     * Note donnée au programme (évaluation).
     */
    @Column
    private int rating;

    /**
     * Retourne l'identifiant du programme.
     *
     * @return L'identifiant du programme.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du programme.
     *
     * @param id L'identifiant du programme.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'identifiant de l'utilisateur associé au programme.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Définit l'identifiant de l'utilisateur associé au programme.
     *
     * @param userId L'identifiant de l'utilisateur.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Retourne l'identifiant de l'activité associée au programme.
     *
     * @return L'identifiant de l'activité.
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * Définit l'identifiant de l'activité associée au programme.
     *
     * @param activityId L'identifiant de l'activité.
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * Retourne la note du programme.
     *
     * @return La note du programme.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Définit la note du programme.
     *
     * @param rating La note à attribuer au programme.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}