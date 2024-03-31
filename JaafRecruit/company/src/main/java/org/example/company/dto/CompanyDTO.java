package org.example.company.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDTO {
    private Long id;
    private Long userId;
    private String name;
    private int employee_number;
    private String description;
    private String website;
    private String industry;
    private Boolean deleted;
}
