package org.example.skills.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.example.skills.entity.Enum.Category;
import org.example.skills.entity.Enum.Level;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillsDTO {
    private Long id;
    private Long userId;
    private String name;
    private Category category;
    private Level level;
    private Boolean deleted;
}
