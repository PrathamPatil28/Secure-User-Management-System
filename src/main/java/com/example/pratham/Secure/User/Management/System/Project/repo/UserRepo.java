package com.example.pratham.Secure.User.Management.System.Project.repo;

import com.example.pratham.Secure.User.Management.System.Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
