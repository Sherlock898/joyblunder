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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "baby_names")
public class BabyName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "Name must be at least 3 characters long")
    private String name;
    @NotBlank
    private String gender;
    @NotBlank
    private String origin;
    @NotBlank
    private String meaning;

    //Creator of the baby name
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //Users who voted for the baby name
    @ManyToMany
    @JoinTable(
        name = "voted_for",
        joinColumns = @JoinColumn(name = "baby_name_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> voters;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public BabyName(){}
    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setGender(String gender){this.gender = gender;}
    public String getGender(){return gender;}
    public void setOrigin(String origin){this.origin = origin;}
    public String getOrigin(){return origin;}
    public void setMeaning(String meaning){this.meaning = meaning;}
    public String getMeaning(){return meaning;}
    public void setUser(User user){this.user = user;}
    public User getUser(){return user;}
    public void setVoters(List<User> voters){this.voters = voters;}
    public List<User> getVoters(){return voters;}
    public Date getCreatedAt() {return createdAt;}
    public Date getUpdatedAt() {return updatedAt;}
}
