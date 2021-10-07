package com.rest.live.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
    User findByUserByEmail(String email);
}
