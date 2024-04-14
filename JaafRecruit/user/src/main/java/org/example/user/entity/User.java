package org.example.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.user.entity.Enum.Role;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userKeycloakId;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDate registrationDate;
    private Role role;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}