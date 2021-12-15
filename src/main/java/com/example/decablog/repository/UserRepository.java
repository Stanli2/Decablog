package com.example.decablog.repository;

import com.example.decablog.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findById(Long id);
    Optional<UserModel> findByEmailAndPassword(String email, String password);
    Optional<UserModel> findByEmail(String email);
}
