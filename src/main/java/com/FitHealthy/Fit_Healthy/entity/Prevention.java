package com.FitHealthy.Fit_Healthy.entity;

import jakarta.persistence.*;

/**
 * Représente une prévention dans le système.
 * Cette classe est mappée à la table "preventions" dans la base de données.
 */
@Entity
@Table(name="preventions")
public class Prevention {

    /**
     * Identifiant unique de la prévention.
     * Ce champ est généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de la prévention.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable=false)
    private String name;

    /**
     * Retourne l'identifiant de la prévention.
     *
     * @return L'identifiant de la prévention.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la prévention.
     *
     * @param id L'identifiant de la prévention.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la prévention.
     *
     * @return Le nom de la prévention.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la prévention.
     *
     * @param name Le nom de la prévention.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructeur de la prévention avec tous les attributs.
     *
     * @param id L'identifiant de la prévention.
     * @param name Le nom de la prévention.
     */
    public Prevention(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }    

    /**
     * Constructeur sans argument.
     * Nécessaire pour l'instanciation via JPA.
     */
    public Prevention() {
    }
}