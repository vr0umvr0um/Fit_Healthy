package com.FitHealthy.Fit_Healthy.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FitHealthy.Fit_Healthy.entity.Role;
import com.FitHealthy.Fit_Healthy.entity.User;
import com.FitHealthy.Fit_Healthy.repository.UserRepository;

/**
 * Service pour charger les données utilisateur et les adapter au format
 * attendu par Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * Constructeur de CustomUserDetailsService.
     *
     * @param userRepository Référence au dépôt utilisateur pour accéder aux données.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Recherche un utilisateur par son email et retourne ses informations de sécurité.
     *
     * @param email L'adresse email de l'utilisateur à rechercher.
     * @return Les détails de l'utilisateur sous forme d'un objet UserDetails.
     * @throws UsernameNotFoundException Si aucun utilisateur correspondant à l'email n'est trouvé.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    /**
     * Mappe les rôles d'un utilisateur en objets GrantedAuthority pour Spring Security.
     *
     * @param roles Collection des rôles associés à l'utilisateur.
     * @return Une collection de GrantedAuthority correspondant aux rôles de l'utilisateur.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
