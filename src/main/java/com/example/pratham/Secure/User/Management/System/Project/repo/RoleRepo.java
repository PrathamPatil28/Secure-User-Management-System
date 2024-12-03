package com.example.pratham.Secure.User.Management.System.Project.repo;

import com.example.pratham.Secure.User.Management.System.Project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo  extends JpaRepository<Role ,Long> {
   Optional<Role>findByName(String name);
}
