package com.example.basicblog.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PostDto {


    private long id;

    @NotNull(message = "title should not be null ")
    @NotBlank(message = "title should not be null or empty")
    private String title;

    @NotNull(message = "description should not be null or empty")
    @Size(min = 10,max = 30,message = "Size of description Should be between 10 and 30 .")
    private String description;

    @NotBlank(message = "content should not be null or empty")
    @Size(min = 10,max = 1000,message = "Size of content Should be between 10 and 30 .")
    private String content;

    @Column(name = "creation_date",updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date" ,insertable = false)
    private LocalDateTime lastUpdate;



    public PostDto() {
    }

    public PostDto(long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
