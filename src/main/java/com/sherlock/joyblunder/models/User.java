package com.sherlock.joyblunder.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @Email
    @NotBlank
    private String email;
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "user")
    private List<BabyName> babyNames;

    @ManyToMany
    @JoinTable(
        name = "voted_for",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "baby_name_id")
    )
    private List<BabyName> votedBabyNames;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public User(){}
    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;} 
    public void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm;}
    public String getPasswordConfirm() {return passwordConfirm;}
    public void setBabyNames(List<BabyName> babyNames) {this.babyNames = babyNames;}
    public List<BabyName> getBabyNames() {return babyNames;}
    public void setVotedBabyNames(List<BabyName> votedBabyNames) {this.votedBabyNames = votedBabyNames;}
    public List<BabyName> getVotedBabyNames() {return votedBabyNames;}    
    public Date getCreatedAt() {return createdAt;}
    public Date getUpdatedAt() {return updatedAt;}
}