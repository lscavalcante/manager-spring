package com.lscavalcante.manager.service.tag;

import com.lscavalcante.manager.dtos.tag.TagCreateRequestDTO;
import com.lscavalcante.manager.dtos.tag.TagDetailResponseDTO;
import com.lscavalcante.manager.infra.mapper.StairsModelMapper;
import com.lscavalcante.manager.model.tag.Tag;
import com.lscavalcante.manager.repository.tag.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final StairsModelMapper stairsModelMapper;

    public TagService(TagRepository tagRepository, StairsModelMapper stairsModelMapper) {
        this.tagRepository = tagRepository;
        this.stairsModelMapper = stairsModelMapper;
    }

    public List<TagDetailResponseDTO> findAll() {

        return tagRepository.findAll().stream().map(e -> stairsModelMapper.map(e, TagDetailResponseDTO.class)).toList();
    }

    public TagDetailResponseDTO createTag(TagCreateRequestDTO data) {

        var tag = stairsModelMapper.map(data, Tag.class);

        tagRepository.save(tag);
        return stairsModelMapper.map(tag, TagDetailResponseDTO.class);
    }
}
