package org.example.jobposting.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostingDTO {
    private Long id;
    private Long companyId;
    private String title;
    private String description;
    private String location;
    private Contract contract;
    private JobType jobType;
    private String salaryRange;
    private String benefits;
    private String requiredSkills;
    private String preferredSkills;
    private String experienceRequirements;
    private String qualifications;
    private LocalDate closingDate;
    private Boolean close;
}