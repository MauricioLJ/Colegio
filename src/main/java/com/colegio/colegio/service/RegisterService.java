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
            boolean existe = estudianteRepository.existeEstudiantePorCedula(estudiante.getCedula());

            if (!existe) {
                estudianteRepository.guardarEstudiante(estudiante);
                logger.info("Estudiante registrado: {} {} {}", estudiante.getNombre(), estudiante.getPrimer_apellido(),
                        estudiante.getSegundo_apellido());
                return "Estudiante registrado con éxito: " + estudiante.getNombre() + " "
                        + estudiante.getPrimer_apellido() + " " + estudiante.getSegundo_apellido();
            } else {
                logger.info("Estudiante con cédula {} ya existe. No se insertó nuevamente.", estudiante.getCedula());
                return "Estudiante ya registrado previamente.";
            }

        } catch (Exception e) {
            logger.error("Error registrando estudiante: {}", estudiante, e);
            throw new RuntimeException("No se pudo registrar el estudiante", e);
        }
    }

    public String registerSubject(estudiantes_materias estudianteMateria) {
        try {
            // Registrar la materia nueva
            materiaRepository.guardarEstudianteMateria(estudianteMateria);

            // Obtener nuevo promedio
            Double promedio = materiaRepository.obtenerPromedioPorCedula(estudianteMateria.getCedula_estudiante());

            if (promedio != null) {
                // Determinar estado según el promedio
                String nuevoEstado = promedio >= 70 ? "Aprobado" : "Reprobado";

                // Actualizar promedio y estado
                estudianteRepository.actualizarNotaYEstado(estudianteMateria.getCedula_estudiante(), promedio,
                        nuevoEstado);
            }

            logger.info("Materia registrada: {} {}", estudianteMateria.getNombre_materia(),
                    estudianteMateria.getTipo_materia());
            return "Materia registrada con éxito: " + estudianteMateria.getNombre_materia() + " "
                    + estudianteMateria.getTipo_materia();
        } catch (Exception e) {
            logger.error("Error registrando materia: {}", estudianteMateria, e);
            throw new RuntimeException("No se pudo registrar la materia", e);
        }
    }

}
