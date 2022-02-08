package com.obinnaogbonna.codechallenge.repository;

import com.obinnaogbonna.codechallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u FROM User u WHERE u.name = ?1")
    public User findByName(String name);
}
