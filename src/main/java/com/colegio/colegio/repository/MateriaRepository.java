package com.colegio.colegio.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
                    (cedula_estudiante, nombre_materia, tipo_materia, nota, estado)
                    VALUES (:cedula_estudiante, :nombreMateria, :tipoMateria, :nota, :estado)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cedula_estudiante", materia.getCedula_estudiante())
                .addValue("nombreMateria", materia.getNombre_materia())
                .addValue("tipoMateria", materia.getTipo_materia())
                .addValue("nota", materia.getNota())
                .addValue("estado", materia.getEstado());

        try {
            return jdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            logger.error("Error al insertar materia del estudiante: {}", materia, ex);
            throw new RuntimeException("Error al guardar materia", ex);
        }
    }

    public Double obtenerPromedioPorCedula(Integer cedulaEstudiante) {
        String sql = """
                    SELECT AVG(nota) FROM estudiantes_materias WHERE cedula_estudiante = :cedula
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cedula", cedulaEstudiante);

        try {
            return jdbcTemplate.queryForObject(sql, params, Double.class);
        } catch (DataAccessException ex) {
            logger.error("Error al obtener promedio de estudiante con cédula: {}", cedulaEstudiante, ex);
            return null;
        }
    }

}
