package com.lscavalcante.manager.model.project;

import com.lscavalcante.manager.enums.StatusEnum;
import com.lscavalcante.manager.model.coment.Comment;
import com.lscavalcante.manager.model.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private StatusEnum status = StatusEnum.CREATED;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToMany
    @JoinTable(
            name = "projects_tags",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "start_date", nullable = true)
    private Date startDate = null;

    @Column(name = "dueDate", nullable = true)
    private Date dueDate = null;

    @OneToMany()
    @JoinColumn(name = "project_id")
    private List<Comment> comments = new ArrayList<>();

    //    @ElementCollection
    //    private List<String> tags;

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProjects().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getProjects().remove(this);
        }
    }
}