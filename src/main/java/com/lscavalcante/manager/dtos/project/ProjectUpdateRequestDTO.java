package com.lscavalcante.manager.dtos.project;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectUpdateRequestDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private List<Long> tagsIds = new ArrayList<>();

    private Date startDate = null;

    private Date dueDate = null;
}