package org.example.experience.dto;

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
    private String companyName;
    private String jobTitle;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean deleted;
}
