package com.colegio.colegio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.colegio.model.estudiantes_materias;

@Repository
public interface MateriaRepository extends JpaRepository<estudiantes_materias, Integer> {

}
