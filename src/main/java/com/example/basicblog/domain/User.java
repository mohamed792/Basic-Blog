package com.example.basicblog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jdk.dynalink.linker.LinkerServices;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    @NotNull
    @NotBlank
    private String firstName;
    @Column(name = "lastName")
    @NotNull
    @NotBlank
    private String lastName;
    @Column(name = "mobile")
    @NotNull
    @NotBlank
    private String mobile;
    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;
    @Column(name = "registeredAt")
    @PastOrPresent
    private LocalDateTime registeredAt;

    @Column(name = "lastLogin")
    @PastOrPresent
    private LocalDateTime lastLogin;
    @Column(name = "intro")
    private String intro;   //The brief introduction of the Author to be displayed on each post.
    @Column(name = "profile")
    private String profile; //The author details to be displayed on the Author Page.

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


}
