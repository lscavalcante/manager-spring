package com.lscavalcante.manager.model.tag;

import com.lscavalcante.manager.model.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Project> projects = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }


}
