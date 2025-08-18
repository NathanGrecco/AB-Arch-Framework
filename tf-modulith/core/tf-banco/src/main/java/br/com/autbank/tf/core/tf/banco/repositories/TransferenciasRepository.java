package br.com.autbank.tf.core.tf.banco.repositories;

import arch.common.config.Config;
import br.com.autbank.tf.core.tf.banco.model.Transferencias;
import jakarta.inject.Named;
import org.springframework.jdbc.core.JdbcTemplate;

@Named
public class TransferenciasRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_TRANSFERENCIAS;

    public TransferenciasRepository(JdbcTemplate jdbcTemplate, Config config) {
        this.jdbcTemplate = jdbcTemplate;
        INSERT_TRANSFERENCIAS = config.getValue("classpath:SQL/DML/INSERT_TRANSFERENCIAS.sql");
    }


    public void registrarTransferencia(Transferencias transferencias) {
        jdbcTemplate.update(INSERT_TRANSFERENCIAS,
                transferencias.getNumeroContaCredito(),
                transferencias.getValor(),
                transferencias.getIdRemessa(),
                transferencias.getDataTransferencia());
    }
}
