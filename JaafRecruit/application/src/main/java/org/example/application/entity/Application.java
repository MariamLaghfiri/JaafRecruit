package org.example.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.application.entity.Enum.AppStatus;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long jobSeekerId;
    private Long jobPostingId;
    private LocalDate applicationDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(45) default 'PENDING'")
    private AppStatus applicationStatus;
}
