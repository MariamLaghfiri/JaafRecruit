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
    private Long userId;
    private String degree;
    private String institution;
    private LocalDate graduationYear;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}