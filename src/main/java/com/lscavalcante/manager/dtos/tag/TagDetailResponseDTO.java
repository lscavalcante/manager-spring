package com.lscavalcante.manager.dtos.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagDetailResponseDTO {
    private Long id;

    private String name;
}
