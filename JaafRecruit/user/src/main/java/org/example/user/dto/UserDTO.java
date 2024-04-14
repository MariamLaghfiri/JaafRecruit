package org.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.user.entity.Enum.Role;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull
    private String userKeycloakId;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDate registrationDate;
    @NotNull
    private Role role;
    @NotNull
    private Boolean deleted;
}
