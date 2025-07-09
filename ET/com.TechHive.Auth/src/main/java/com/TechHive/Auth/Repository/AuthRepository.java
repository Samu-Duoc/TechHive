package com.TechHive.Auth.Repository;

import com.TechHive.Auth.Model.Auth;


import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthRepository extends JpaRepository<Auth, Long> {
    
}
