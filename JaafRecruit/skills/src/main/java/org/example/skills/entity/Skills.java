package org.example.skills.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.skills.entity.Enum.Category;
import org.example.skills.entity.Enum.Level;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private Category category;
    private Level level;
    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private Boolean deleted;
}
