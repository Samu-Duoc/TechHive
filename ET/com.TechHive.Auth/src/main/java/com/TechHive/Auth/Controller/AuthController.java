package com.TechHive.Auth.Controller;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
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

    
    @PutMapping("/{id}")
    public Auth updateAuth(@PathVariable Long id, @RequestBody Auth auth){
        auth.setId(id);
        return authService.save(auth);
    }

    @DeleteMapping("/{id}")
    public void deleteAuth(@PathVariable Long id) {
        authService.deleteById(id);
    }
}
