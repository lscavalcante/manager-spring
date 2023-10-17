package com.lscavalcante.manager.dtos.project;

import com.lscavalcante.manager.dtos.tag.TagCreateRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCreateRequestDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    private Set<Long> tagIds = new HashSet<>();

    private boolean isPublic = false;

    private Date startDate = null;

    private Date dueDate = null;
}