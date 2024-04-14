package org.example.user.dto;

import lombok.*;
import org.example.user.entity.Enum.Role;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserKeycloakRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
