package com.obinnaogbonna.codechallenge.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany()
    @JoinTable(
            name="USER_TASK",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "TASK_ID", referencedColumnName = "id", unique = true)}
    )
    private List<Task> tasks;

    @Column(name = "score")
    private Integer score;
}
