package org.example.jobseeker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.jobseeker.entity.Enum.Availability;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String coverLater;
    private String resume;
    private String desiredJobTitles;
    private String desiredSalaryRange;
    private Availability availability;
    @Column(name="job_alert", columnDefinition = "boolean default false")
    private Boolean jobAlert;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}