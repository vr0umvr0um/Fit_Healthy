package com.FitHealthy.Fit_Healthy.entity;

import java.util.List;

import jakarta.persistence.*;

/**
 * Représente une entité Rôle associée aux utilisateurs du système.
 * Un rôle peut être attribué à plusieurs utilisateurs.
 * Cette entité est mappée à la table "roles" dans la base de données.
 */
@Entity
@Table(name = "roles")
public class Role {
    
    /**
     * Identifiant unique du Rôle.
     * Ce champ est généré automatiquement et est utilisé comme clé primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Le nom du rôle.
     * Ce champ doit être unique et ne peut pas être nul.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Liste des utilisateurs qui ont ce rôle attribué.
     * Cette relation est de type plusieurs-à-plusieurs et est mappée par l'attribut "roles" de l'entité User.
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    /**
     * Récupère l'identifiant unique du rôle.
     * 
     * @return l'id du rôle.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique pour le rôle.
     * 
     * @param id l'id à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom du rôle.
     * 
     * @return le nom du rôle.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du rôle.
     * 
     * @param name le nom à définir.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère la liste des utilisateurs associés à ce rôle.
     * 
     * @return la liste des utilisateurs associés à ce rôle.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Définit la liste des utilisateurs associés à ce rôle.
     * 
     * @param users la liste des utilisateurs à définir.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Construit un nouveau rôle avec l'id, le nom et les utilisateurs associés donnés.
     * 
     * @param id l'identifiant unique du rôle.
     * @param name le nom du rôle.
     * @param users la liste des utilisateurs associés au rôle.
     */
    public Role(Long id, String name, List<User> users) {
        super();
        this.id = id;
        this.name = name;
        this.users = users;
    }

    /**
     * Constructeur par défaut pour l'entité Rôle.
     */
    public Role() {
        super();
    }
}