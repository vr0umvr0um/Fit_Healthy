package com.FitHealthy.Fit_Healthy.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Représente une entité Utilisateur dans le système.
 * Un utilisateur a des informations personnelles telles que son prénom, son nom, son sexe, son âge, sa pathologie, son email et son mot de passe.
 * De plus, un utilisateur peut avoir plusieurs rôles assignés à lui, ce qui définit ses permissions dans le système.
 * Cette entité est mappée à la table "users" dans la base de données.
 */
@Entity
@Table(name = "users")
public class User {
    
    /**
     * Le numéro de version pour la sérialisation de l'objet.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifiant unique de l'Utilisateur.
     * Ce champ est généré automatiquement et est utilisé comme clé primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Le prénom de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Le nom de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Le sexe de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String gender;

    /**
     * L'âge de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private Integer age;

    /**
     * La pathologie (si applicable) associée à l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String pathology;

    /**
     * L'adresse email de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Le mot de passe de l'utilisateur.
     * Ce champ ne peut pas être nul.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Liste des rôles assignés à l'utilisateur.
     * Cette relation est de type plusieurs-à-plusieurs et elle est mappée par l'attribut "roles" dans l'entité `Role`.
     * Les rôles sont récupérés de manière anticipée (EAGER) et toutes les opérations sont propagées vers les entités `Role`.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles = new ArrayList<>();

    /**
     * Récupère l'identifiant unique de l'utilisateur.
     * 
     * @return l'id de l'utilisateur.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de l'utilisateur.
     * 
     * @param id l'id à définir.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le prénom de l'utilisateur.
     * 
     * @return le prénom de l'utilisateur.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Définit le prénom de l'utilisateur.
     * 
     * @param firstName le prénom à définir.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Récupère le nom de l'utilisateur.
     * 
     * @return le nom de l'utilisateur.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Définit le nom de l'utilisateur.
     * 
     * @param lastName le nom à définir.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Récupère le sexe de l'utilisateur.
     * 
     * @return le sexe de l'utilisateur.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Définit le sexe de l'utilisateur.
     * 
     * @param gender le sexe à définir.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Récupère l'âge de l'utilisateur.
     * 
     * @return l'âge de l'utilisateur.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Définit l'âge de l'utilisateur.
     * 
     * @param age l'âge à définir.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Récupère la pathologie associée à l'utilisateur.
     * 
     * @return la pathologie de l'utilisateur.
     */
    public String getPathology() {
        return pathology;
    }

    /**
     * Définit la pathologie associée à l'utilisateur.
     * 
     * @param pathology la pathologie à définir.
     */
    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    /**
     * Récupère l'email de l'utilisateur.
     * 
     * @return l'email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'email de l'utilisateur.
     * 
     * @param email l'email à définir.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     * 
     * @return le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * 
     * @param password le mot de passe à définir.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Récupère la liste des rôles attribués à l'utilisateur.
     * 
     * @return la liste des rôles associés à l'utilisateur.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Définit la liste des rôles attribués à l'utilisateur.
     * 
     * @param roles la liste des rôles à définir.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Récupère le numéro de version pour la sérialisation de l'objet.
     * 
     * @return le numéro de version.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
