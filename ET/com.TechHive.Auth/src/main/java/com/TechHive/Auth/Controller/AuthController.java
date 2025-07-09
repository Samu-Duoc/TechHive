package com.TechHive.Auth.Controller;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;


@RestController

@RequestMapping("/api/auth")

@Tag(name = "Auth", description = "Login de Auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Auth> getAllAuths() {
        return authService.findAll();
    }

    @GetMapping("/{id}")
    public Auth getAuthById(@PathVariable Long id) {
        return authService.findById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Auth> register(@RequestBody Auth request) {
        Auth response = authService.save(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuth(@PathVariable Long id) {
        authService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
