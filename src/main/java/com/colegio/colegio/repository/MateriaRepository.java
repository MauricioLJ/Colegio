package com.colegio.colegio.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.colegio.colegio.model.estudiantes_materias;

@Repository
public class MateriaRepository {

    private static final Logger logger = LoggerFactory.getLogger(MateriaRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MateriaRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int guardarEstudianteMateria(estudiantes_materias materia) {
        String sql = """
                    INSERT INTO estudiantes_materias
                    (id_estudiante, nombre_materia, tipo_materia, nota)
                    VALUES (:idEstudiante, :nombreMateria, :tipoMateria, :nota)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("idEstudiante", materia.getId_estudiante())
                .addValue("nombreMateria", materia.getNombre_materia())
                .addValue("tipoMateria", materia.getTipo_materia())
                .addValue("nota", materia.getNota());

        try {
            return jdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            logger.error("Error al insertar materia del estudiante: {}", materia, ex);
            throw new RuntimeException("Error al guardar materia", ex);
        }
    }

}
