package br.com.autbank.tf.core.tf.banco.model.apis;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CadastrarEnvioBrokerRequest {

    private Long idRemessa;
    private BigDecimal valor;
}
