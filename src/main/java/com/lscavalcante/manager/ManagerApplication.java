package com.lscavalcante.manager;

import com.lscavalcante.manager.model.tag.Tag;
import com.lscavalcante.manager.repository.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManagerApplication {

    final private TagRepository tagRepository;

    public ManagerApplication(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);

    }

    @Bean
    void initProject() {
        tagRepository.save(new Tag("manager".toUpperCase()));
        tagRepository.save(new Tag("office".toUpperCase()));
        tagRepository.save(new Tag("healthy".toUpperCase()));
        tagRepository.save(new Tag("software".toUpperCase()));
    }

}
