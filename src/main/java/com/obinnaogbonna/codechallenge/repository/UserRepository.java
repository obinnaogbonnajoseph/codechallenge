package com.obinnaogbonna.codechallenge.repository;

import com.obinnaogbonna.codechallenge.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u FROM User u WHERE u.name = ?1")
    public User findByName(String name);

    public List<User> findAllByScore(Pageable pageable);
}
