package org.example.experience.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String companyName;
    private String jobTitle;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}
