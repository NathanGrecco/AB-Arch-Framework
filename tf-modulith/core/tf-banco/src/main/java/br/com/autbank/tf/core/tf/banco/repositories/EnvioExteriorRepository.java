package br.com.autbank.tf.core.tf.banco.repositories;

import arch.common.config.Config;
import br.com.autbank.tf.core.tf.banco.model.EnvioExterior;
import br.com.autbank.tf.core.tf.banco.model.StatusEnvioExterior;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Named
@Slf4j
public class EnvioExteriorRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT;
    private final String UPDATE_STATUS;
    private final String SELECT_STATUS_LIBERADO;

    public EnvioExteriorRepository(JdbcTemplate jdbcTemplate, Config config) {
        this.jdbcTemplate = jdbcTemplate;
        this.INSERT = config.getValue("classpath:SQL/DML/INSERT_ENVIOS_EXTERIOR.sql");
        this.UPDATE_STATUS = config.getValue("classpath:SQL/DML/UPDATE_STATUS_ENVIOS_EXTERIOR.sql");
        this.SELECT_STATUS_LIBERADO = config.getValue("classpath:SQL/DQL/SELECT_STATUS_LIBERADO.sql");
    }

    public EnvioExterior registrarEnvioExterior(EnvioExterior envioExterior) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, envioExterior.getIdRemessa());
            ps.setBigDecimal(2, envioExterior.getValor());
            ps.setString(3, envioExterior.getStatus().getDescricao());
            ps.setTimestamp(4, Timestamp.valueOf(envioExterior.getDataStatus()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if (key != null) {
            envioExterior.setId(key.longValue());
        }

        return envioExterior;
    }

    public void atualizaStatusEnviosExterior(StatusEnvioExterior status, Long idRemessa) {
        jdbcTemplate.update(UPDATE_STATUS, status.getDescricao(), idRemessa);
    }

    public List<EnvioExterior> listaRegistrosLiberado(StatusEnvioExterior statusEnvioExterior) {
        return jdbcTemplate.query(SELECT_STATUS_LIBERADO,
                (rs, rownum) -> EnvioExterior.builder()
                        .idRemessa(rs.getLong("ID_REMESSA"))
                        .valor(rs.getBigDecimal("VALOR"))
                        .build(),
                statusEnvioExterior.getDescricao());
    }
}
