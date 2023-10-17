package com.lscavalcante.manager.controller;

import com.lscavalcante.manager.dtos.comment.CommentCreateRequestDTO;
import com.lscavalcante.manager.dtos.project.ProjectCreateRequestDTO;
import com.lscavalcante.manager.dtos.project.ProjectDetailResponseDTO;
import com.lscavalcante.manager.dtos.project.ProjectUpdateRequestDTO;
import com.lscavalcante.manager.service.comment.CommentService;
import com.lscavalcante.manager.service.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/projects")
@Validated
public class
ProjectController {
    private final ProjectService projectService;
    private final CommentService commentService;

    public ProjectController(ProjectService projectService, CommentService commentService) {
        this.projectService = projectService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectDetailResponseDTO>> list() {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<ProjectDetailResponseDTO> createProject(@RequestBody @Valid ProjectCreateRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(data));
    }

    @PutMapping("{projectId}")
    public ResponseEntity<ProjectDetailResponseDTO> updateProject(@PathVariable Long projectId, @RequestBody @Valid ProjectUpdateRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.updateProject(projectId, data));
    }

    @PostMapping("{projectId}/create-comment")
    public ResponseEntity<ProjectDetailResponseDTO> createComment(@PathVariable Long projectId, @RequestBody @Valid CommentCreateRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createCommentToProject(projectId, data));
    }

    @PatchMapping("{projectId}/finish-project")
    public ResponseEntity<ProjectDetailResponseDTO> finishProject(@PathVariable Long projectId) {

        return ResponseEntity.status(HttpStatus.OK).body(projectService.finishProject(projectId));
    }

    @DeleteMapping("{projectId}/remove-comment/{commentId}")
    public ResponseEntity<Map<String, String>> finishProject(@PathVariable Long projectId, @PathVariable Long commentId) {
        Map<String, String> body = new HashMap<>();
        body.put("message", "deleted with success");

        projectService.removeComment(projectId, commentId);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
