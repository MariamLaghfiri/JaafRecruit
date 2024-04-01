package org.example.application.dto;

import lombok.*;
import org.example.application.entity.Enum.AppStatus;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationDTO {
    private Long id;
    private Long jobSeekerId;
    private Long jobPostingId;
    private LocalDate applicationDate;
    private AppStatus applicationStatus;
}