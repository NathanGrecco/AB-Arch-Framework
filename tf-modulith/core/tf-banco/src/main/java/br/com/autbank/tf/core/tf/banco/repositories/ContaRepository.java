package br.com.autbank.tf.core.tf.banco.repositories;

import arch.common.config.Config;
import jakarta.inject.Named;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

@Named
public class ContaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SALDO_SUFICIENTE;
    private final String UPDATE_SUBTRAI_SALDO;
    private final String UPDATE_ADICIONA_SALDO;

    public ContaRepository(JdbcTemplate jdbcTemplate, Config config) {
        this.jdbcTemplate = jdbcTemplate;
        this.SALDO_SUFICIENTE = config.getValue("classpath:SQL/DQL/SELECT_SALDO_SUFICIENTE.sql");
        this.UPDATE_SUBTRAI_SALDO = config.getValue("classpath:SQL/DML/UPDATE_SUBTRAI_SALDO.sql");
        this.UPDATE_ADICIONA_SALDO = config.getValue("classpath:SQL/DML/UPDATE_ADICIONA_SALDO.sql");
    }

    public void adicionaSaldo(String nroConta, BigDecimal valor) {
        jdbcTemplate.update(UPDATE_ADICIONA_SALDO, valor, nroConta);
    }
    public void subtraiSaldo(String nroConta, BigDecimal valor) {
        jdbcTemplate.update(UPDATE_SUBTRAI_SALDO, valor, nroConta);
    }

    public boolean verificaSaldoSuficiente(String nroConta, BigDecimal valor) {
        List<Integer> result = jdbcTemplate.query(SALDO_SUFICIENTE, (rs, rowNum) -> rs.getInt(1), nroConta, valor);
        return !result.isEmpty();
    }
}
