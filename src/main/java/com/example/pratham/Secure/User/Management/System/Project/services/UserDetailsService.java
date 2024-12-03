package com.example.pratham.Secure.User.Management.System.Project.services;

import com.example.pratham.Secure.User.Management.System.Project.dto.CreateUserRequest;
import com.example.pratham.Secure.User.Management.System.Project.dto.UpdateUserRequest;
import com.example.pratham.Secure.User.Management.System.Project.dto.UserDto;
import com.example.pratham.Secure.User.Management.System.Project.model.Role;
import com.example.pratham.Secure.User.Management.System.Project.model.User;
import com.example.pratham.Secure.User.Management.System.Project.repo.RoleRepo;
import com.example.pratham.Secure.User.Management.System.Project.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

    public UserDetailsService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDto convertToUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserDto createUser(CreateUserRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(mapRoles(request.getRoles()));

        User savedUser = userRepo.save(user);
        return new UserDto( savedUser.getId(), savedUser.getUsername(),savedUser.getEmail());

    }

//    private Set<Role> mapRoles(Set<String> roleNames) {
//        return roleNames.stream()
//                .map(roleName -> roleRepo.findByName(roleName)
//                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
//                .collect(Collectors.toSet());
//    }
private Set<Role> mapRoles(Set<String> roleNames) {
    return roleNames.stream()
            .map(roleName -> roleRepo.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
            .collect(Collectors.toSet());
}


    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }


    // Update user details
    public UserDto updateUser(Long id, UpdateUserRequest request) {
        // Find the existing user
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // Update fields
        existingUser.setUsername(request.getUsername());
        existingUser.setEmail(request.getEmail());
        if (request.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        existingUser.setRoles(mapRoles(request.getRoles()));

        // Save the updated user
        User updatedUser = userRepo.save(existingUser);

        // Convert and return the updated user as UserDTO
        return new UserDto(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
    }

    // Delete a user
    public void deleteUser(Long id) {
        // Check if the user exists
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // Delete the user from the repository
        userRepo.delete(user);
    }
}
