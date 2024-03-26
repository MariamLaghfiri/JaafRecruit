package org.example.education.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    private String degree;
    private String institution;
    private LocalDate graduation_year;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}