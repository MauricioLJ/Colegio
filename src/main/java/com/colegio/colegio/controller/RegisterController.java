package com.colegio.colegio.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.colegio.model.estudiantes;
import com.colegio.colegio.model.estudiantes_materias;
import com.colegio.colegio.service.RegisterService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

        private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

        private final RegisterService registerService;

        public RegisterController(RegisterService registerService) {
                this.registerService = registerService;
        }

        @PostMapping("/registerNew")
        public Map<String, String> register(@RequestBody Map<String, Object> request) {
                Map<String, Object> estudianteData = (Map<String, Object>) request.get("estudiante");
                Map<String, Object> materiaData = (Map<String, Object>) request.get("materia");

                estudiantes estudiante = new estudiantes(
                                null,
                                (String) estudianteData.get("nombre"),
                                (String) estudianteData.get("primer_apellido"),
                                (String) estudianteData.get("segundo_apellido"),
                                (Integer) estudianteData.get("nota"),
                                (String) estudianteData.get("estado"),
                                (String) estudianteData.get("fecha_nacimiento"));

                estudiantes_materias materia = new estudiantes_materias(
                                null,
                                (Integer) materiaData.get("id_estudiante"),
                                (String) materiaData.get("nombre_materia"),
                                (String) materiaData.get("tipo_materia"),
                                (Integer) materiaData.get("nota"));

                registerService.registerStudent(estudiante);
                registerService.registerSubject(materia);

                Map<String, String> response = new LinkedHashMap<>();
                response.put("mensajeEstudiante", "Estudiante registrado con éxito: " +
                                (String) estudianteData.get("nombre") + " " +
                                (String) estudianteData.get("primer_apellido") + " " +
                                (String) estudianteData.get("segundo_apellido"));
                response.put("mensajeMateria", "Materia registrada con éxito: " +
                                materia.getNombre_materia() + " " + materia.getTipo_materia());

                return response;
        }

}
