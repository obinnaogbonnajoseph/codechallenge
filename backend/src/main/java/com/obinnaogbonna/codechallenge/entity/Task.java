package com.obinnaogbonna.codechallenge.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "initCode")
    private String starterCode;

    @Column(name = "answer")
    private String answer;

    @ManyToMany(mappedBy = "tasks")
    @JsonManagedReference
    List<User> users;
    
}
