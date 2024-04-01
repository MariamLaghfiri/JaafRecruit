package org.example.jobposting.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean close;
}