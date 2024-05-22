package com.example.demo.controllers;

import com.example.demo.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/SGI/Dashboard/")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Map<String, Long> counts = new HashMap<>();
        counts.put("utilisateurs", dashboardService.countUtilisateurs());
        counts.put("annexes", dashboardService.countAnnexes());
        counts.put("districts", dashboardService.countDistricts());
        counts.put("categories", dashboardService.countCategories());
        counts.put("endroits", dashboardService.countEndroitsByUtilisateur(currentUserName));
        return ResponseEntity.ok(counts);
    }
}
