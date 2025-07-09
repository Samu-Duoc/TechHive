package com.TechHive.Auth.Service;

import com.TechHive.Auth.Model.Auth;
import com.TechHive.Auth.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public List<Auth> findAll() {
        return authRepository.findAll();
    }

    public Auth findById(Long id) {
        return authRepository.findById(id).orElse(null);
    }

    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }

    public void deleteById(Long id) {
        authRepository.findById(id);
    }
}
