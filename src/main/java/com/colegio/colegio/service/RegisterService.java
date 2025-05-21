package com.colegio.colegio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colegio.colegio.model.estudiantes;
import com.colegio.colegio.model.estudiantes_materias;
import com.colegio.colegio.repository.EstudianteRepository;
import com.colegio.colegio.repository.MateriaRepository;

@Service
@Transactional
public class RegisterService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    public String registerStudent(estudiantes estudiante) {

        estudianteRepository.save(estudiante);

        return "Estudiante registrado con exito: " + estudiante.getNombre() + " " + estudiante.getPrimer_apellido()
                + " " + estudiante.getSegundo_apellido();
    }

    public String registerSubject(estudiantes_materias estudiante_materias) {

        materiaRepository.save(estudiante_materias);

        return "Materia registrada con exito: " + estudiante_materias.getNombre_materia() + " "
                + estudiante_materias.getTipo_materia();
    }

}
