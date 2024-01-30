package com.spring.security.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private int isActive;


    private String firstName;
    private String lastName;

    private String imagePath;


    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @OrderColumn(name = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public AppUser(String username, String password, int isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

}
