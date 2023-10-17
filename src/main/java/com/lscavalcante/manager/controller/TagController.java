package com.lscavalcante.manager.controller;

import com.lscavalcante.manager.dtos.tag.TagCreateRequestDTO;
import com.lscavalcante.manager.dtos.tag.TagDetailResponseDTO;
import com.lscavalcante.manager.model.tag.Tag;
import com.lscavalcante.manager.service.tag.TagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tags")
@Validated
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("")
    public ResponseEntity<TagDetailResponseDTO> createTag(@RequestBody @Valid TagCreateRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.createTag(data));
    }

    @GetMapping("")
    public ResponseEntity<List<TagDetailResponseDTO>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.findAll());
    }
}
