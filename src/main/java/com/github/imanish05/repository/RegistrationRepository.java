package com.github.imanish05.repository;

import com.github.imanish05.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);

}
