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
    private Long userId;
    private String degree;
    private String institution;
    private LocalDate graduationYear;
    private Boolean deleted;
}
