package org.example.experience.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExperienceDTO {
    private Long id;
    private Long userId;
    private String company_name;
    private String job_title;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private Boolean deleted;
}
