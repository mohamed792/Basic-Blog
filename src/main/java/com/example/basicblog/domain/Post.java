package com.example.basicblog.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "posts" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column(name = "title")
private String title;
@Column(name = "description")
private String description;
@Column(name = "content")
private String content;

@Column(name = "creation_date",updatable = false)
private LocalDateTime creationDate;

@Column(name = "update_date")
private LocalDateTime lastUpdate;




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
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
