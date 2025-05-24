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
                    (cedula, nombre, primer_apellido, segundo_apellido, nota, estado, fecha_nacimiento)
                    VALUES (:cedula, :nombre, :primerApellido, :segundoApellido, :nota, :estado, :fechaNacimiento)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cedula", estudiante.getCedula())
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

    public void actualizarNotaEstudiante(Integer cedula, Double promedio) {
        String sql = """
                    UPDATE estudiantes SET nota = :promedio WHERE cedula = :cedula
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cedula", cedula)
                .addValue("promedio", promedio);

        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            logger.error("Error al actualizar nota promedio del estudiante: {}", cedula, ex);
        }
    }

    public boolean existeEstudiantePorCedula(Integer cedula) {
        String sql = "SELECT COUNT(*) FROM estudiantes WHERE cedula = :cedula";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("cedula", cedula);

        try {
            Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
            return count != null && count > 0;
        } catch (DataAccessException ex) {
            logger.error("Error verificando existencia de estudiante con cédula {}", cedula, ex);
            return false;
        }
    }

    public void actualizarNotaYEstado(Integer cedula, Double promedio, String estado) {
        String sql = """
                    UPDATE estudiantes SET nota = :promedio, estado = :estado WHERE cedula = :cedula
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cedula", cedula)
                .addValue("promedio", promedio)
                .addValue("estado", estado);

        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException ex) {
            logger.error("Error al actualizar nota y estado del estudiante: {}", cedula, ex);
        }
    }

}