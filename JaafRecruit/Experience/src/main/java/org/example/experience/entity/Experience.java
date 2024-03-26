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
    private Long id_user;
    private String company_name;
    private String job_title;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}
