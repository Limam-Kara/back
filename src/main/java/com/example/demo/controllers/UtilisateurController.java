package com.example.demo.controllers;

import com.example.demo.dtos.UtilisateurDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;
import com.example.demo.Service.UtilisateurService;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SGI/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/save")
    public ResponseEntity<?> saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur savedUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
            UtilisateurDTO savedUtilisateurDto = toDto(savedUtilisateur);
            return new ResponseEntity<>(savedUtilisateurDto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Utilisateur> getUtilisateurByUsername(@PathVariable String username) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByUsername(username);
            if (utilisateur != null) {
                return new ResponseEntity<>(utilisateur, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            if (utilisateur != null) {
                return new ResponseEntity<>(utilisateur, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/authenticated")
    public ResponseEntity<Utilisateur> getAuthenticatedUser() {
        try {
            Utilisateur utilisateur = utilisateurService.getAuthenticatedUser();
            if (utilisateur != null) {
                return new ResponseEntity<>(utilisateur, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
            return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAll();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            // Attempt to edit the utilisateur
            Utilisateur updatedUtilisateur = utilisateurService.editUtilisateur(utilisateur);
            UtilisateurDTO updatedUtilisateurDto = toDto(updatedUtilisateur);
            return new ResponseEntity<>(updatedUtilisateurDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Handle runtime exceptions (e.g., utilisateur not found, conflict)
            if (e.getMessage().contains("Utilisateur with ID")) {
                // Utilisateur not found
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            } else if (e.getMessage().contains("Another utilisateur")) {
                // Conflict with existing utilisateur
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            } else {
                // Other runtime exceptions
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Exception handler for UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private UtilisateurDTO toDto(Utilisateur utilisateur) {
        return new UtilisateurDTO(
                utilisateur.getId(),
                utilisateur.getPpr(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getFonction(),
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getTel(),
                utilisateur.getGenre(),
                utilisateur.getAdresse(),
                utilisateur.getDateDeNaissance(),
                utilisateur.getIdRole().getId()
        );
    }
}

