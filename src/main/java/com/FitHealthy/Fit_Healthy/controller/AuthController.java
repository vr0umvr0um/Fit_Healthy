package com.FitHealthy.Fit_Healthy.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.FitHealthy.Fit_Healthy.dto.ActivityDto;
import com.FitHealthy.Fit_Healthy.dto.DisciplineDto;
import com.FitHealthy.Fit_Healthy.dto.PreventionDto;
import com.FitHealthy.Fit_Healthy.dto.ProgramDto;
import com.FitHealthy.Fit_Healthy.dto.UserDto;
import com.FitHealthy.Fit_Healthy.entity.User;
import com.FitHealthy.Fit_Healthy.service.ActivityService;
import com.FitHealthy.Fit_Healthy.service.PreventionService;
import com.FitHealthy.Fit_Healthy.service.ProgramService;
import com.FitHealthy.Fit_Healthy.service.UserService;

import jakarta.validation.Valid;

/**
 * Contrôleur pour gérer les actions liées à l'authentification, l'inscription, 
 * et la gestion des utilisateurs ainsi que des programmes.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PreventionService prevService;
    @Autowired
    private ActivityService actService;
    @Autowired
    private ProgramService progService;

    /**
     * Affiche la page d'accueil. Si l'utilisateur est déjà authentifié, 
     * il est redirigé vers la page des activités.
     * 
     * @param authentication l'authentification de l'utilisateur
     * @return la vue à afficher
     */
    @GetMapping("/index")
    public String home(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/activities";
        }
        
        return "index";
    }
    
    /**
     * Affiche la page de connexion. Si l'utilisateur est déjà authentifié, 
     * il est redirigé vers la page des activités.
     * 
     * @param authentication l'authentification de l'utilisateur
     * @return la vue de connexion
     */
    @GetMapping("/login")
    public String login(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/activities";
        }
        
        return "login";
    }

    /**
     * Affiche la page d'inscription. Si l'utilisateur est déjà authentifié, 
     * il est redirigé vers la page des activités.
     * 
     * @param model le modèle pour ajouter des attributs à la vue
     * @param authentication l'authentification de l'utilisateur
     * @return la vue d'inscription
     */
    @GetMapping("/register")
    public String register(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/activities";
        }
        
        UserDto user = new UserDto();
        List<PreventionDto> prevs = prevService.findAllPreventions();
        model.addAttribute("user", user);
        model.addAttribute("prevs", prevs);
        return "register";
    }

    /**
     * Sauvegarde un nouvel utilisateur après validation du formulaire d'inscription.
     * Si un utilisateur avec le même email existe déjà, une erreur est ajoutée.
     * 
     * @param userDto les données de l'utilisateur à enregistrer
     * @param result les résultats de la validation du formulaire
     * @param model le modèle pour ajouter des attributs à la vue
     * @return la vue de redirection ou de rechargement de la page d'inscription
     */
    @PostMapping("/register/save")
    public String registerSave(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model){

        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "L'adresse email a déjà été utilisée");
        }
        
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/login?success";
    }
    
    /**
     * Affiche la page d'administration.
     * 
     * @return la vue d'administration
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin(){
        return "admin";
    }

    /**
     * Affiche la liste de tous les utilisateurs.
     * 
     * @param model le modèle pour ajouter des attributs à la vue
     * @return la vue avec la liste des utilisateurs
     */
    @GetMapping("/admin-users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin-users";
    }
    
    /**
     * Sauvegarde un nouvel utilisateur après validation du formulaire d'inscription.
     * Si un utilisateur avec le même email existe déjà, une erreur est ajoutée.
     * 
     * @param userDto les données de l'utilisateur à enregistrer
     * @param result les résultats de la validation du formulaire
     * @param model le modèle pour ajouter des attributs à la vue
     * @return la vue de redirection ou de rechargement de la page d'inscription
     */
    @PostMapping("/add-to-admin")
    public String addAdmin(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model){

        userService.saveUserAsAdmin(userDto);
        return "redirect:/admin-users?success";
    }

    /**
     * Affiche le compte utilisateur. Montre les programmes associés à l'utilisateur,
     * ainsi que la moyenne des notes de ses programmes.
     * 
     * @param model le modèle pour ajouter des attributs à la vue
     * @param authentication l'authentification de l'utilisateur
     * @return la vue du compte utilisateur
     */
    @GetMapping("/user-account")
    public String userAccount(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findUserByEmail(username);
        if (user != null) {
            model.addAttribute("user", user);
            List<ProgramDto> programs = progService.findAllProgramsByUserId(user.getId());
            model.addAttribute("programs", programs);
            Double averageRating = progService.calculateAverageRatingByUserId(user.getId());
            model.addAttribute("averageRating", averageRating != null ? String.format("%.2f", averageRating) : "Non évalué");
        }
        return "user-account";
    }

    /**
     * Met à jour la note d'un programme pour un utilisateur.
     * 
     * @param programId l'identifiant du programme
     * @param rating la nouvelle note pour le programme
     * @return la vue de redirection vers le compte utilisateur
     */
    @PostMapping("/update-program-rating")
    public String updateProgramRating(@RequestParam("programId") Long programId,
            @RequestParam("rating") int rating) {
        progService.updateProgramRating(programId, rating);
        return "redirect:/user-account";
    }

    /**
     * Met à jour les informations personnelles de l'utilisateur.
     * 
     * @param userDto les nouvelles informations de l'utilisateur
     * @param authentication l'authentification de l'utilisateur
     * @return la vue de redirection vers le compte utilisateur avec un message de succès
     */
    @PostMapping("/user-update")
    public String userUpdate(@ModelAttribute("user") UserDto userDto,
            Authentication authentication) {
        String username = authentication.getName();
        User currentUser = userService.findUserByEmail(username);
        userDto.setEmail(currentUser.getEmail());
        userService.updateUser(userDto);
        return "redirect:/user-account?success";
    }

    /**
     * Affiche la liste des activités disponibles.
     * 
     * @param model le modèle pour ajouter des attributs à la vue
     * @param authentication l'authentification de l'utilisateur
     * @return la vue des activités
     */
    @GetMapping("/activities")
    public String activities(Model model, Authentication authentication) {
        
    	addCommonAttributes(model, authentication);
       
        List<ActivityDto> activities = actService.getAllActivitiesWithPreventionsAndDisciplines();
        model.addAttribute("activities", activities);
        return "activities";
    }

    /**
     * Ajoute une activité au programme de l'utilisateur.
     * 
     * @param activityId l'identifiant de l'activité
     * @param userId l'identifiant de l'utilisateur
     * @param rating la note de l'activité
     * @return la vue de redirection vers la page des activités
     */
    @PostMapping("/add-to-program")
    public String addToProgram(@RequestParam("activityId") Long activityId,
            @RequestParam("userId") Long userId,
            @RequestParam("rating") int rating) {
        progService.addProgram(userId, activityId, rating);
        return "redirect:/activities?success";
    }

    /**
     * Recherche les activités selon les critères spécifiés par l'utilisateur.
     * 
     * @param rechercheP la pathologie à rechercher
     * @param rechercheD la discipline à rechercher
     * @param model le modèle pour ajouter des attributs à la vue
     * @param authentication l'authentification de l'utilisateur
     * @return la vue des activités filtrées
     */
    @PostMapping("/search-activities")
    public String searchActivities(
            @RequestParam(required = false) String rechercheP,
            @RequestParam(required = false) String rechercheD,
            Model model,
            Authentication authentication) {

    	String pathology;
    	if (rechercheP == null || rechercheP.isBlank()) {
    	    pathology = null;
    	} else {
    	    pathology = rechercheP;
    	}

    	String discipline;
    	if (rechercheD == null || rechercheD.isBlank()) {
    	    discipline = null;
    	} else {
    	    discipline = rechercheD;
    	}
      
        List<ActivityDto> filteredActivities = actService.searchActivities(pathology, discipline);
        model.addAttribute("activities", filteredActivities);
        addCommonAttributes(model, authentication);
        return "activities";
    }

    /**
     * Ajoute les attributs communs nécessaires pour l'affichage des activités.
     * 
     * @param model le modèle pour ajouter des attributs à la vue
     * @param authentication l'authentification de l'utilisateur
     */
    private void addCommonAttributes(Model model, Authentication authentication) {
        List<ActivityDto> allActivities = actService.getAllActivitiesWithPreventionsAndDisciplines();
        
        Set<String> uniqueDisciplines = allActivities.stream()
                .flatMap(activity -> activity.getDiscips().stream())
                .map(DisciplineDto::getName)
                .collect(Collectors.toSet());
        
        Set<String> uniquePreventions = allActivities.stream()
                .flatMap(activity -> activity.getPrevs().stream())
                .map(PreventionDto::getName)
                .collect(Collectors.toSet());
        
        model.addAttribute("uniqueDisciplines", uniqueDisciplines);
        model.addAttribute("uniquePreventions", uniquePreventions);

        String username = authentication.getName();
        User user = userService.findUserByEmail(username);
        if (user != null) {
            model.addAttribute("userId", user.getId());
            String userPathology = user.getPathology();
            List<ActivityDto> recommendedActivities = allActivities.stream()
                    .filter(activity -> activity.getPrevs().stream()
                            .anyMatch(prev -> prev.getName().equalsIgnoreCase(userPathology)))
                    .collect(Collectors.toList());
            model.addAttribute("recommendedActivities", recommendedActivities);
        }
    }
}