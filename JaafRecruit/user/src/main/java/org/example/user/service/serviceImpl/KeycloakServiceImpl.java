package org.example.user.service.serviceImpl;

import jakarta.ws.rs.core.Response;
import org.example.user.dto.LoginDTO;
import org.example.user.dto.RefreshTokenDTO;
import org.example.user.dto.UserKeycloakRequestDto;
import org.example.user.entity.Enum.Role;
import org.example.user.service.KeycloakService;
import org.example.user.util.KeycloakUtil;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakServiceImpl  implements KeycloakService {

    @Value("${keycloak.credentials.secret}")
    private String SECRETKEY;

    @Value("${keycloak.resource}")
    private String CLIENTID;

    @Value("${keycloak.server-url}")
    private String SERVERURL;

    @Value("${keycloak.realm}")
    private String REALM;

    @Autowired
    public KeycloakServiceImpl( KeycloakUtil keycloakUtil) {
        this.keycloakUtil = keycloakUtil;
    }

    private final KeycloakUtil keycloakUtil;


    @Override
    public String createUserInKeycloak(UserKeycloakRequestDto userRequest) throws RuntimeException {
        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = buildUserRepresentation(userRequest);

        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Keycloak user creation failed");
        }

        return CreatedResponseUtil.getCreatedId(response);
    }

    private UserRepresentation buildUserRepresentation(UserKeycloakRequestDto userRequest) {
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(userRequest.getPassword());
        passwordCredential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCredentials(List.of(passwordCredential));

        user.setEnabled(true);
        user.setEmailVerified(false);

        return user;
    }
    public void assignRoleToUser(String userId, Role role) {
        String roleName = role.name();

        // add new role
        RoleRepresentation roleRepresentation = keycloakUtil.getRealmResource().roles().get(roleName).toRepresentation();
        keycloakUtil.getRealmResource().users().get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));

    }


    // check if access token is valid
    @Override
    public boolean validateToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Basic Authentication with client credentials
        String clientCredentials = Base64.getEncoder().encodeToString((CLIENTID + ":" + SECRETKEY).getBytes());
        headers.set("Authorization", "Basic " + clientCredentials);

        // Prepare request parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token_type_hint", "requesting_party_token");
        map.add("token", accessToken);

        // Create HTTP entity
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        // Construct introspection endpoint URL
        String introspectUrl = SERVERURL + "/realms/" + REALM + "/protocol/openid-connect/token/introspect";

        try {
            // Make the request and receive the response
            ResponseEntity<Map> response = restTemplate.exchange(
                    introspectUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            // Check if the token is active
            Map<String, Object> responseBody = response.getBody();
            return responseBody != null && Boolean.TRUE.equals(responseBody.get("active"));
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return false;
        }
    }


    // get the access token from the keycloak
    @Override
    public String getToken(LoginDTO loginDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", CLIENTID);
        map.add("client_secret", SECRETKEY);
        map.add("username", loginDTO.getUsername());
        map.add("password", loginDTO.getPassword());
        map.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String tokenUrl = SERVERURL + "/realms/" + REALM + "/protocol/openid-connect/token";

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    tokenUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Handle 401 Unauthorized response
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            }
            throw e;
        }
    }


    // get the access token with the refresh token from the keycloak
    @Override
    public String refreshToken(RefreshTokenDTO refreshTokenDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", CLIENTID);
        map.add("client_secret", SECRETKEY);
        map.add("refresh_token", refreshTokenDTO.getRefreshToken());  // Assuming getRefreshToken() exists in RefreshTokenDTO
        map.add("grant_type", "refresh_token");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String tokenUrl = SERVERURL + "/realms/" + REALM + "/protocol/openid-connect/token";

        ResponseEntity<String> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.getBody();
    }

}
