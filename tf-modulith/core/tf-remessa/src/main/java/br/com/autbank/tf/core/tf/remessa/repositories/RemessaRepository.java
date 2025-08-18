package br.com.autbank.tf.core.tf.remessa.repositories;

import arch.common.config.Config;
import br.com.autbank.tf.core.tf.remessa.model.Remessas;
import br.com.autbank.tf.core.tf.remessa.model.StatusRemessa;
import jakarta.inject.Named;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Named
public class RemessaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT;
    private final String SELECT_STATUS;
    private final String EXISTE;
    private final String UPDATE_STATUS;
    private final String SELECT;

    public RemessaRepository(JdbcTemplate jdbcTemplate, Config config) {
        this.jdbcTemplate = jdbcTemplate;
        INSERT = config.getValue("classpath:SQL/DML/INSERT_REMESSAS.sql");
        SELECT_STATUS = config.getValue("classpath:SQL/DQL/SELECT_STATUS_REMESSAS.sql");
        EXISTE = config.getValue("classpath:SQL/DQL/SELECT_EXISTE_REMESSAS.sql");
        UPDATE_STATUS = config.getValue("classpath:SQL/DML/UPDATE_STATUS_REMESSAS.sql");
        SELECT = config.getValue("classpath:SQL/DQL/SELECT_REMESSA_POR_ID.sql");
    }

    public Remessas registrarRemessa(Remessas remessas) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, remessas.getValorReais());
            ps.setBigDecimal(2, remessas.getValorDolar());
            ps.setBigDecimal(3, remessas.getCotacaoDolar());
            ps.setString(4, remessas.getStatus().getDescricao());
            ps.setTimestamp(5, Timestamp.valueOf(remessas.getDataStatus()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if (key != null) {
            remessas.setId(key.longValue());
        }

        return remessas;
    }

    public String recuperaStatusRemessa(Long id) {
        return jdbcTemplate.queryForObject(SELECT_STATUS, String.class, id);
    }

    public Remessas recuperaRemessaPorId(Long id) {
        return jdbcTemplate.queryForObject(SELECT,
                (rs, rowNum) -> Remessas.builder()
                        .valorDolar(rs.getBigDecimal("VALOR_DOLAR"))
                        .valorReais(rs.getBigDecimal("VALOR_REAIS"))
                        .status(StatusRemessa.fromDescricao(rs.getString("STATUS")))
                        .dataStatus(rs.getTimestamp("DATA_STATUS").toLocalDateTime())
                        .cotacaoDolar(rs.getBigDecimal("COTACAO_DOLAR"))
                        .build(),
                id);
    }

    public boolean existeRemessa(Long id) {
        List<Integer> result = jdbcTemplate.query(EXISTE, (rs, rowNum) -> rs.getInt(1), id);
        return !result.isEmpty();
    }

    public void atualizaStatusRemessa(StatusRemessa status, Long idRemessa) {
        jdbcTemplate.update(UPDATE_STATUS, status.getDescricao(), idRemessa);
    }
}
