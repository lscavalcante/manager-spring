package com.lscavalcante.manager.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailResponseDTO {
    private Long id;

    private String description;
}
