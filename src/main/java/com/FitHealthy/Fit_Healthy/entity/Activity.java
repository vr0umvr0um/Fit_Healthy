package com.FitHealthy.Fit_Healthy.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Représente une activité dans le système, associée à des préventions et des disciplines.
 * Cette classe est mappée à la table "activities" dans la base de données.
 */
@Entity
@Table(name="activities")
public class Activity {
    
    /**
     * Identifiant unique de l'activité.
     * Ce champ est généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de l'activité.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable=false)
    private String name;
    
    /**
     * Liste des préventions associées à cette activité.
     * La relation est de type Many-to-Many avec la table "preventions".
     * Les entités "Prevention" sont chargées de manière immédiate (EAGER).
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="activities_preventions",
            joinColumns={@JoinColumn(name="ACTIVITY_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PREVENTION_ID", referencedColumnName="ID")})
    private List<Prevention> prevs = new ArrayList<>();
    
    /**
     * Liste des disciplines associées à cette activité.
     * La relation est de type Many-to-Many avec la table "disciplines".
     * Les entités "Discipline" sont chargées de manière immédiate (EAGER).
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="activities_discipline",
            joinColumns= {@JoinColumn(name="ACTIVITY_ID", referencedColumnName="ID")},
            inverseJoinColumns= {@JoinColumn(name="DISCIPLINE_ID", referencedColumnName="ID")})
    private List<Discipline> discips = new ArrayList<>();

    /**
     * Retourne l'identifiant de l'activité.
     *
     * @return L'identifiant de l'activité.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'activité.
     *
     * @param id L'identifiant de l'activité.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'activité.
     *
     * @return Le nom de l'activité.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de l'activité.
     *
     * @param name Le nom de l'activité.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la liste des préventions associées à l'activité.
     *
     * @return La liste des préventions.
     */
    public List<Prevention> getPrevs() {
        return prevs;
    }

    /**
     * Définit la liste des préventions associées à l'activité.
     *
     * @param prevs La liste des préventions.
     */
    public void setPrevs(List<Prevention> prevs) {
        this.prevs = prevs;
    }

    /**
     * Retourne la liste des disciplines associées à l'activité.
     *
     * @return La liste des disciplines.
     */
    public List<Discipline> getDiscips() {
        return discips;
    }

    /**
     * Définit la liste des disciplines associées à l'activité.
     *
     * @param discips La liste des disciplines.
     */
    public void setDiscips(List<Discipline> discips) {
        this.discips = discips;
    }

    /**
     * Constructeur de l'activité avec tous les attributs.
     *
     * @param id L'identifiant de l'activité.
     * @param name Le nom de l'activité.
     * @param prevs La liste des préventions associées.
     * @param discips La liste des disciplines associées.
     */
    public Activity(Long id, String name, List<Prevention> prevs, List<Discipline> discips) {
        super();
        this.id = id;
        this.name = name;
        this.prevs = prevs;
        this.discips = discips;
    }

    /**
     * Constructeur sans argument.
     * Nécessaire pour l'instanciation via JPA.
     */
    public Activity() {
    }
}