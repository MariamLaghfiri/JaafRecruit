package org.example.jobseeker.dto;

import lombok.*;
import org.example.jobseeker.entity.Enum.Availability;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSeekerDTO {
    private Long id;
    private Long userId;
    private String coverLater;
    private String resume;
    private String desiredJobTitles;
    private String desiredSalaryRange;
    private Availability availability;
    private Boolean jobAlert;
    private Boolean deleted;
}