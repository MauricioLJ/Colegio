package com.colegio.colegio.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.colegio.colegio.model.estudiantes;

@Repository
public class EstudianteRepository {

    private static final Logger logger = LoggerFactory.getLogger(MateriaRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EstudianteRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int guardarEstudiante(estudiantes estudiante) {
        String sql = """
                    INSERT INTO estudiantes
                    (nombre, primer_apellido, segundo_apellido, nota, estado, fecha_nacimiento)
                    VALUES (:nombre, :primerApellido, :segundoApellido, :nota, :estado, :fechaNacimiento)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nombre", estudiante.getNombre())
                .addValue("primerApellido", estudiante.getPrimer_apellido())
                .addValue("segundoApellido", estudiante.getSegundo_apellido())
                .addValue("nota", estudiante.getNota())
                .addValue("estado", estudiante.getEstado())
                .addValue("fechaNacimiento", estudiante.getFecha_nacimiento());

        try {
            return jdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            logger.error("Error al insertar estudiante: {}", estudiante, ex);
            throw new RuntimeException("Error al guardar estudiante", ex);
        }
    }

}