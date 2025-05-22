package com.colegio.colegio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colegio.colegio.model.estudiantes;
import com.colegio.colegio.model.estudiantes_materias;
import com.colegio.colegio.repository.EstudianteRepository;
import com.colegio.colegio.repository.MateriaRepository;

@Service
@Transactional
public class RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    private final EstudianteRepository estudianteRepository;
    private final MateriaRepository materiaRepository;

    public RegisterService(EstudianteRepository estudianteRepository, MateriaRepository materiaRepository) {
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    public String registerStudent(estudiantes estudiante) {
        try {
            estudianteRepository.guardarEstudiante(estudiante);
            logger.info("Estudiante registrado: {} {} {}", estudiante.getNombre(), estudiante.getPrimer_apellido(),
                    estudiante.getSegundo_apellido());
            return "Estudiante registrado con éxito: " + estudiante.getNombre() + " " + estudiante.getPrimer_apellido()
                    + " " + estudiante.getSegundo_apellido();
        } catch (Exception e) {
            logger.error("Error registrando estudiante: {}", estudiante, e);
            throw new RuntimeException("No se pudo registrar el estudiante", e);
        }
    }

    public String registerSubject(estudiantes_materias estudiante_materias) {
        try {
            materiaRepository.guardarEstudianteMateria(estudiante_materias);
            logger.info("Materia registrada: {} {}", estudiante_materias.getNombre_materia(),
                    estudiante_materias.getTipo_materia());
            return "Materia registrada con éxito: " + estudiante_materias.getNombre_materia() + " "
                    + estudiante_materias.getTipo_materia();
        } catch (Exception e) {
            logger.error("Error registrando materia: {}", estudiante_materias, e);
            throw new RuntimeException("No se pudo registrar la materia", e);
        }
    }

}
