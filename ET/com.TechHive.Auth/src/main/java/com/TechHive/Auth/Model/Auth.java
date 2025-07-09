package com.TechHive.Auth.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "auth")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Auth {
    @Id
    private Long id;
    private String correo;
    private String password;
    private String rol;

}

