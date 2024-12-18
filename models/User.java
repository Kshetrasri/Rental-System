package com.crio.rentvideo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Video> rentals;
}
