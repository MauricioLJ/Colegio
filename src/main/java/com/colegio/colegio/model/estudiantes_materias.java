package com.colegio.colegio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class estudiantes_materias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estudiantemateria;
    private Integer cedula_estudiante;
    private String nombre_materia;
    private String tipo_materia;
    private Integer nota;
    private String estado;
}
