package com.FitHealthy.Fit_Healthy.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.FitHealthy.Fit_Healthy.dto.UserDto;
import com.FitHealthy.Fit_Healthy.entity.Role;
import com.FitHealthy.Fit_Healthy.entity.User;
import com.FitHealthy.Fit_Healthy.repository.RoleRepository;
import com.FitHealthy.Fit_Healthy.repository.UserRepository;
import com.FitHealthy.Fit_Healthy.service.UserService;

/**
 * Implémentation du service UserService.
 * Fournit des méthodes pour gérer les utilisateurs, y compris l'enregistrement, la recherche et la mise à jour des informations des utilisateurs.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passEncod;

    /**
     * Enregistre un nouvel utilisateur.
     * 
     * @param userDto Les données de l'utilisateur à enregistrer sous forme de DTO.
     */
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGender(userDto.getGender());
        user.setAge(userDto.getAge());
        user.setPathology(userDto.getPathology());
        user.setEmail(userDto.getEmail());
        
        // Chiffrement du mot de passe avec Spring Security
        user.setPassword(passEncod.encode(userDto.getPassword()));

        Role role = roleRepo.findByName("USER");
        if (role == null) {
            role = checkRoleUserExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }
    
    /**
     * Enregistre un nouvel utilisateur en tant qu'admin.
     * 
     * @param userDto Les données de l'utilisateur à enregistrer sous forme de DTO.
     */
    @Override
	public void saveUserAsAdmin(UserDto userDto) {
    	// Récupérer l'utilisateur existant à partir de son email
        User existingUser = userRepo.findByEmail(userDto.getEmail());
        
        if (existingUser != null) {
         Role role = roleRepo.findByName("ADMIN");
         if (role == null) {
             role = checkRoleAdminExist();
         }
         existingUser.setRoles(Arrays.asList(role));
         userRepo.save(existingUser);
        }
	}



	/**
     * Recherche un utilisateur par son email.
     * 
     * @param email L'email de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'email, ou null si aucun utilisateur n'est trouvé.
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Récupère tous les utilisateurs.
     * 
     * @return Une liste de DTO d'utilisateurs.
     */
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     * 
     * @param userDto Les nouvelles informations de l'utilisateur sous forme de DTO.
     */
    @Override
    public void updateUser(UserDto userDto) {
        // Récupérer l'utilisateur existant à partir de son email
        User existingUser = userRepo.findByEmail(userDto.getEmail());
        
        if (existingUser != null) {
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            
            // Chiffrer le mot de passe avant de le sauvegarder
            existingUser.setPassword(passEncod.encode(userDto.getPassword()));
            
            existingUser.setGender(userDto.getGender());
            existingUser.setAge(userDto.getAge());
            existingUser.setPathology(userDto.getPathology());
            
            // Sauvegarder l'utilisateur mis à jour
            userRepo.save(existingUser);
        }
    }

    /**
     * Convertit une entité User en un DTO UserDto.
     * 
     * @param user L'entité User à convertir.
     * @return Le DTO correspondant à l'entité User.
     */
    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.getGender());
        userDto.setAge(user.getAge());
        userDto.setPathology(user.getPathology());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    /**
     * Vérifie si le rôle "ADMIN" existe, et le crée si nécessaire.
     * 
     * @return Le rôle "ADMIN" nouvellement créé.
     */
    private Role checkRoleAdminExist() {
        Role role = new Role();
        role.setName("ADMIN");
        return roleRepo.save(role);
    }
    
    /**
     * Vérifie si le rôle "USER" existe, et le crée si nécessaire.
     * 
     * @return Le rôle "USER" nouvellement créé.
     */
    private Role checkRoleUserExist() {
        Role role = new Role();
        role.setName("USER");
        return roleRepo.save(role);
    }
}