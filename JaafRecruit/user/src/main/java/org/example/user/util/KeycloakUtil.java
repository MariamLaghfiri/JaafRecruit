package org.example.user.util;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtil {

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private Keycloak keycloakInstance;

    /**
     * Get Keycloak instance
     * If instance is not already created, create a new one
     * @return
     */
    public Keycloak getKeycloakInstance() {
        if (keycloakInstance == null) {
            keycloakInstance = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        }
        return keycloakInstance;
    }

    /**
     * Get Realm resource
     * @return
     */
    public RealmResource getRealmResource() {
        return getKeycloakInstance().realm(realm);
    }
}