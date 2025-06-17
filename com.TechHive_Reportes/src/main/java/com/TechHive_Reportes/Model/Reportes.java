package com.TechHive_Reportes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reportes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipoDeReporte;
    private LocalDateTime fechaDeGenracion;

    @Lob
    private String contenido; // JSON o CSV serilizado

    @PrePersist
    public void prePersist(){
        this.fechaDeGenracion = LocalDateTime.now();
    }
}
