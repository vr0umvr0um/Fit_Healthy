package com.FitHealthy.Fit_Healthy.entity;

import jakarta.persistence.*;

/**
 * Représente une discipline dans le système. 
 * Cette classe est mappée à la table "disciplines" dans la base de données.
 */
@Entity
@Table(name="disciplines")
public class Discipline {

    /**
     * Identifiant unique de la discipline.
     * Ce champ est généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de la discipline.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable=false)
    private String name;

    /**
     * Retourne l'identifiant de la discipline.
     *
     * @return L'identifiant de la discipline.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la discipline.
     *
     * @param id L'identifiant de la discipline.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la discipline.
     *
     * @return Le nom de la discipline.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la discipline.
     *
     * @param name Le nom de la discipline.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructeur de la discipline avec tous les attributs.
     *
     * @param id L'identifiant de la discipline.
     * @param name Le nom de la discipline.
     */
    public Discipline(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }    

    /**
     * Constructeur sans argument.
     * Nécessaire pour l'instanciation via JPA.
     */
    public Discipline() {
    }
}