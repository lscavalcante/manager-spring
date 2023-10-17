package com.lscavalcante.manager.repository.tag;

import com.lscavalcante.manager.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findAllByIdIn(Set<Long> ids);
}
