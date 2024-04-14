package org.example.user.controller;

import jakarta.validation.Valid;
import org.example.user.dto.LoginDTO;
import org.example.user.dto.RefreshTokenDTO;
import org.example.user.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeycloakController {

    @Autowired
    KeycloakService keycloakService;

    // Get the token from keycloak endPoint
    @PostMapping("/auth/keycloak/token")
    public ResponseEntity<String> getToken(@RequestBody @Valid LoginDTO loginDTO) {
        String token = keycloakService.getToken(loginDTO);
        return ResponseEntity.ok(token);
    }

    // Get the access token with the refresh token
    @PostMapping("/auth/keycloak/refresh-token")
    public ResponseEntity<String> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        String newToken = keycloakService.refreshToken(refreshTokenDTO);
        if (newToken != null) {
            return ResponseEntity.ok(newToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to refresh token");
        }
    }
}