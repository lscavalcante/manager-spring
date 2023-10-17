package com.lscavalcante.manager.dtos.tag;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagCreateRequestDTO {

    @NotEmpty
    private String name;
}
