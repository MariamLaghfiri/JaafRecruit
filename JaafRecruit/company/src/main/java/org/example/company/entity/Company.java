package org.example.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private int employeeNumber;
    private String description;
    private String website;
    private String industry;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}
