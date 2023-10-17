package com.lscavalcante.manager.dtos.project;

import com.lscavalcante.manager.dtos.comment.CommentDetailResponseDTO;
import com.lscavalcante.manager.dtos.tag.TagDetailResponseDTO;
import com.lscavalcante.manager.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponseDTO {

    private Long id;

    private String title;

    private String description;

    private Set<TagDetailResponseDTO> tags;

    private boolean isPublic;

    private Date startDate;

    private Date dueDate;

    private StatusEnum status;

    private List<CommentDetailResponseDTO> comments;
}
