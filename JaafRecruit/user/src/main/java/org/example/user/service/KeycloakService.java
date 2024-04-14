package org.example.user.service;

import jakarta.ws.rs.NotFoundException;
import org.example.user.dto.LoginDTO;
import org.example.user.dto.RefreshTokenDTO;
import org.example.user.dto.UserDTO;
import org.example.user.dto.UserKeycloakRequestDto;
import org.example.user.entity.Enum.Role;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface KeycloakService {
    String createUserInKeycloak(UserKeycloakRequestDto userRequest) throws RuntimeException;

    void assignRoleToUser(String userKeycloakId, Role role);

    // check if access token is valid
    boolean validateToken(String accessToken);

    // get the access token from the keycloak
    String getToken(LoginDTO loginDTO);

    // get the access token with the refresh token from the keycloak
    String refreshToken(RefreshTokenDTO refreshTokenDTO);
}
