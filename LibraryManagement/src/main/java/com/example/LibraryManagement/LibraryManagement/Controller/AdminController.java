package com.example.LibraryManagement.LibraryManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagement.LibraryManagement.DTO.LoginRequestDTO;
import com.example.LibraryManagement.LibraryManagement.DTO.LoginResponseDTO;
import com.example.LibraryManagement.LibraryManagement.DTO.RegisterRequestDTO;
import com.example.LibraryManagement.LibraryManagement.Entity.User;
import com.example.LibraryManagement.LibraryManagement.Service.AuthenticationService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

    

}
