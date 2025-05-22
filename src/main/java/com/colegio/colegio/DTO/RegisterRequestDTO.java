package com.colegio.colegio.DTO;

import com.colegio.colegio.model.estudiantes;
import com.colegio.colegio.model.estudiantes_materias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private estudiantes estudiantes;
    private estudiantes_materias estudiantes_materias;
}
