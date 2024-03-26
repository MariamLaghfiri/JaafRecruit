package org.example.education.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationDTO {
    private Long id;
    private Long id_user;
    private String degree;
    private String institution;
    private LocalDate graduation_year;
    private Boolean deleted;
}
