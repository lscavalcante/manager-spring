package com.lscavalcante.manager.service.project;

import com.lscavalcante.manager.dtos.project.ProjectCreateRequestDTO;
import com.lscavalcante.manager.dtos.project.ProjectDetailResponseDTO;
import com.lscavalcante.manager.dtos.project.ProjectUpdateRequestDTO;
import com.lscavalcante.manager.enums.StatusEnum;
import com.lscavalcante.manager.infra.mapper.StairsModelMapper;
import com.lscavalcante.manager.model.project.Project;
import com.lscavalcante.manager.model.tag.Tag;
import com.lscavalcante.manager.repository.comment.CommentRepository;
import com.lscavalcante.manager.repository.project.ProjectRepository;
import com.lscavalcante.manager.repository.tag.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final StairsModelMapper stairsModelMapper;
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    public ProjectService(ProjectRepository projectRepository, StairsModelMapper stairsModelMapper, TagRepository tagRepository, CommentRepository commentRepository) {
        this.projectRepository = projectRepository;
        this.stairsModelMapper = stairsModelMapper;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    public ProjectDetailResponseDTO createProject(ProjectCreateRequestDTO data) {
        logger.info("Criando projeto com nome {}", data.getTitle());

        var project = stairsModelMapper.map(data, Project.class);

        projectRepository.save(project);

        Set<Tag> tags = tagRepository.findAllByIdIn(data.getTagIds());

        for (var tag : tags) {
            project.addTag(tag);
        }

        projectRepository.save(project);

        return stairsModelMapper.map(project, ProjectDetailResponseDTO.class);
    }

    public ProjectDetailResponseDTO updateProject(Long projectId, ProjectUpdateRequestDTO data) {
        try {
            logger.info("Atualizando o projeto com nome {}", data.getTitle());

            Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));


            // Mapeie os IDs das tags existentes no projeto atual
            Set<Long> existingTagIds = project.getTags().stream().map(Tag::getId).collect(Collectors.toSet());

            // Remova as tags que não estão mais presentes
            project.getTags().removeIf(tag -> !data.getTagsIds().contains(tag.getId()));

            // adiciona novas tags o projeto se existe
            for (var tagId : new HashSet<>(data.getTagsIds())) {
                tagRepository.findById(tagId).ifPresent(tag -> {
                    if (!project.getTags().contains(tag)) {
                        project.addTag(tag);
//                        project.getTags().add(tag);
                    }
                });
            }

            var tagsUpdated = project.getTags();
            stairsModelMapper.map(data, project);
            project.setTags(tagsUpdated);
            projectRepository.saveAndFlush(project);

            return stairsModelMapper.map(project, ProjectDetailResponseDTO.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("error " + ex.getMessage());
        }
    }

    public List<ProjectDetailResponseDTO> findAll() {
        logger.info("Searching all projects");

        var temporaryProjects = projectRepository.findAll();
        System.out.println(temporaryProjects);

        var projects = projectRepository.findAll().stream().map(e -> stairsModelMapper.map(e, ProjectDetailResponseDTO.class)).toList();

        return projects;
    }

    public ProjectDetailResponseDTO finishProject(Long projectId) {
        logger.info("Projeto com o id {} finalizado com sucesso", projectId);

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        if (Objects.equals(project.getStatus().getStatus(), StatusEnum.FINISHED.getStatus())) {
            throw new RuntimeException("You cannot do this, the project is already with the status FINISHED");
        }

        project.setStatus(StatusEnum.FINISHED);
        projectRepository.save(project);

        return stairsModelMapper.map(project, ProjectDetailResponseDTO.class);
    }

    public void removeComment(Long projectId, Long commentId) {
        commentRepository.findByIdAndProjectId(commentId, projectId).ifPresentOrElse(
                commentRepository::delete,
                () -> {
                    throw new RuntimeException("Comment not found");
                }
        );
    }
}
