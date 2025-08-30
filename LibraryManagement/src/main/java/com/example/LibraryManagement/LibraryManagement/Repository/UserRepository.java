package com.example.LibraryManagement.LibraryManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagement.LibraryManagement.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByUsername(String username);
}
