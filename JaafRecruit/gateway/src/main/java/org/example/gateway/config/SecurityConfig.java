package org.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final String JOB_SEEKER = "JOB-SEEKER";
    private final String ADMIN = "ADMIN";
    private final String RECRUITER = "RECRUITER";

    @Bean
    public SecurityWebFilterChain springSecurituFilterChain (ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/user/add").permitAll()
                        .pathMatchers("/auth/keycloak/token").permitAll()
                        .pathMatchers("/auth/keycloak/refresh-token").permitAll()
                        .pathMatchers("/skills/**").hasRole(JOB_SEEKER)
                        .pathMatchers("/education/**").hasRole(JOB_SEEKER)
                        .pathMatchers("/experience/**").hasRole(JOB_SEEKER)
                        .pathMatchers("/company/add").hasRole(RECRUITER)
                        .pathMatchers("/company/update/").hasRole(RECRUITER)
                        .pathMatchers("/company/all").hasRole(ADMIN)
                        .pathMatchers("/company/delete/").hasRole(ADMIN)
                        .pathMatchers("/company/{id}").hasRole(ADMIN)
                        .pathMatchers("/jobPosting/all-available").hasAnyRole(ADMIN, JOB_SEEKER)
                        .pathMatchers("/jobPosting/all").hasRole(ADMIN)
                        .pathMatchers("/jobPosting/get-by-contract/").hasAnyRole(ADMIN, JOB_SEEKER)
                        .pathMatchers("/jobPosting/get-by-job-type/").hasAnyRole(ADMIN, JOB_SEEKER)
                        .pathMatchers("/jobPosting/get-by-company-id/").hasAnyRole(ADMIN, JOB_SEEKER, RECRUITER)
                        .pathMatchers("/jobPosting/{id}").hasAnyRole(ADMIN, JOB_SEEKER, RECRUITER)
                        .pathMatchers("/jobPosting/add").hasRole(ADMIN)
                        .pathMatchers("/jobPosting/update/").hasRole(ADMIN)
                        .pathMatchers("/jobPosting/close/").hasRole(ADMIN)
                        .pathMatchers("/application/all-by-post-id/").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/application/all-by-user-id").hasAnyRole(ADMIN, JOB_SEEKER)
                        .pathMatchers("/application/accept/").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/application/reject/").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/application/get-by-accepted").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/application/{id}").hasAnyRole(ADMIN, JOB_SEEKER, RECRUITER)
                        .pathMatchers("/application/add").hasAnyRole(ADMIN, JOB_SEEKER)
                        .pathMatchers("/jobSeeker/all").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/jobSeeker/get-by-availability/").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/jobSeeker/{id}").hasAnyRole(ADMIN, RECRUITER)
                        .pathMatchers("/jobSeeker/add").hasRole(JOB_SEEKER)
                        .pathMatchers("/jobSeeker/update/").hasRole(JOB_SEEKER)
                        .pathMatchers("/jobSeeker/delete/").hasRole(ADMIN)
                        .pathMatchers("/user/delete/").hasRole(ADMIN)
                        .pathMatchers("/user/all").hasRole(ADMIN)
                        .pathMatchers("/user/{id}").hasRole(ADMIN)
                        .pathMatchers("/user/update/").hasAnyRole(ADMIN, JOB_SEEKER, RECRUITER)
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}