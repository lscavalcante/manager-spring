package com.lscavalcante.manager.service.comment;

import com.lscavalcante.manager.dtos.comment.CommentCreateRequestDTO;
import com.lscavalcante.manager.dtos.project.ProjectDetailResponseDTO;
import com.lscavalcante.manager.infra.mapper.StairsModelMapper;
import com.lscavalcante.manager.model.coment.Comment;
import com.lscavalcante.manager.model.project.Project;
import com.lscavalcante.manager.repository.comment.CommentRepository;
import com.lscavalcante.manager.repository.project.ProjectRepository;
import com.lscavalcante.manager.service.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;
    private final StairsModelMapper stairsModelMapper;

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository, StairsModelMapper stairsModelMapper, ProjectRepository projectRepository) {
        this.commentRepository = commentRepository;
        this.stairsModelMapper = stairsModelMapper;
        this.projectRepository = projectRepository;
    }

    public ProjectDetailResponseDTO createCommentToProject(Long projectId, CommentCreateRequestDTO data) {

        logger.info("Criando o comentario com nome {} para o projeto {}", data.getDescription(), projectId);

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        var comment = stairsModelMapper.map(data, Comment.class);
        comment.setProject(project);

        commentRepository.save(comment);

        return stairsModelMapper.map(project, ProjectDetailResponseDTO.class);
    }
}
