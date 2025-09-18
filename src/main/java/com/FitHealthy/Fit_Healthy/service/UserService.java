package com.FitHealthy.Fit_Healthy.service;

import java.util.List;

import com.FitHealthy.Fit_Healthy.dto.UserDto;
import com.FitHealthy.Fit_Healthy.entity.User;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 */
public interface UserService {

    /**
     * Sauvegarde un nouvel utilisateur ou met à jour un utilisateur existant.
     * 
     * @param userDto L'objet UserDto contenant les informations de l'utilisateur.
     */
    void saveUser(UserDto userDto);

    /**
     * Recherche un utilisateur dans la base de données en fonction de son adresse email.
     * 
     * @param email L'adresse email de l'utilisateur.
     * @return L'objet User correspondant à l'adresse email, ou null si aucun utilisateur n'est trouvé.
     */
    User findUserByEmail(String email);

    /**
     * Récupère tous les utilisateurs sous forme d'une liste d'objets UserDto.
     * 
     * @return Une liste d'objets UserDto représentant tous les utilisateurs enregistrés.
     */
    List<UserDto> findAllUsers();

    /**
     * Met à jour les informations d'un utilisateur existant.
     * 
     * @param userDto L'objet UserDto contenant les informations mises à jour de l'utilisateur.
     */
    void updateUser(UserDto userDto);

    /**
     * Sauvegarde un nouvel utilisateur ou met à jour un utilisateur existant en tant qu'admin.
     * 
     * @param userDto L'objet UserDto contenant les informations de l'utilisateur.
     */
	void saveUserAsAdmin(UserDto userDto);
}
