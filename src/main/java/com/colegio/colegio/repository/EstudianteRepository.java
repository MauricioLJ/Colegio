package com.colegio.colegio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.colegio.model.estudiantes;

@Repository
public interface EstudianteRepository extends JpaRepository<estudiantes, Integer> {
}