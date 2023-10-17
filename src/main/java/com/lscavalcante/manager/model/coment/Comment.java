package com.lscavalcante.manager.model.coment;

import com.lscavalcante.manager.model.project.Project;
import jakarta.persistence.*;


@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String description;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    public Comment() {
    }

    public Comment(Long id, String description, boolean isActive, String createdBy, Project project) {
        this.id = id;
        this.description = description;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}