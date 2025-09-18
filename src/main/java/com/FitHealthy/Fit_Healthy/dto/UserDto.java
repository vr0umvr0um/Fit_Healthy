package com.FitHealthy.Fit_Healthy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Représente un DTO (Data Transfer Object) pour l'entité Utilisateur.
 * Ce DTO contient les informations nécessaires sur un utilisateur,
 * comme son nom, prénom, genre, âge, pathologie, email, et mot de passe.
 * Il est utilisé pour transférer les données entre la couche de présentation et la couche de service ou de stockage.
 */
public class UserDto {

    /**
     * L'identifiant unique de l'utilisateur.
     * Cet identifiant est utilisé pour distinguer un utilisateur des autres dans le système.
     */
    private Long id;

    /**
     * Le prénom de l'utilisateur.
     * Ce champ ne doit pas être vide.
     */
    @NotEmpty
    private String firstName;

    /**
     * Le nom de famille de l'utilisateur.
     * Ce champ ne doit pas être vide.
     */
    @NotEmpty
    private String lastName;

    /**
     * Le genre de l'utilisateur.
     * Ce champ ne doit pas être vide.
     */
    @NotEmpty
    private String gender;

    /**
     * L'âge de l'utilisateur.
     * Ce champ ne doit pas être nul.
     */
    @NotNull
    private Integer age;

    /**
     * La pathologie de l'utilisateur (si applicable).
     * Ce champ ne doit pas être vide.
     */
    @NotEmpty
    private String pathology;

    /**
     * L'email de l'utilisateur.
     * Ce champ ne doit pas être vide et doit respecter le format d'un email.
     */
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    /**
     * Le mot de passe de l'utilisateur.
     * Ce champ ne doit pas être vide.
     */
    @NotEmpty(message = "Password should not be empty")
    private String password;

    /**
     * Récupère l'identifiant de l'utilisateur.
     * 
     * @return l'identifiant de l'utilisateur.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     * 
     * @param id l'identifiant de l'utilisateur.
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
     * Récupère le nom de famille de l'utilisateur.
     * 
     * @return le nom de famille de l'utilisateur.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Définit le nom de famille de l'utilisateur.
     * 
     * @param lastName le nom de famille à définir.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Récupère le genre de l'utilisateur.
     * 
     * @return le genre de l'utilisateur.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Définit le genre de l'utilisateur.
     * 
     * @param gender le genre à définir.
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
     * Récupère la pathologie de l'utilisateur (si applicable).
     * 
     * @return la pathologie de l'utilisateur.
     */
    public String getPathology() {
        return pathology;
    }

    /**
     * Définit la pathologie de l'utilisateur.
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
     * Retourne une représentation sous forme de chaîne de caractères de l'objet UserDto.
     * 
     * @return une chaîne de caractères représentant l'objet UserDto.
     */
    @Override
    public String toString() {
        return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
                + ", age=" + age + ", pathology=" + pathology + ", email=" + email + ", password=" + password + "]";
    }
}