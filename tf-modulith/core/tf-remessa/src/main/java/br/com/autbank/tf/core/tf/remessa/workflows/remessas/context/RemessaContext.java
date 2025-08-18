package br.com.autbank.tf.core.tf.remessa.workflows.remessas.context;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class RemessaContext {

    Long id;
    BigDecimal taxaCotacao;
    BigDecimal valor;
    BigDecimal valorEnvio;
}
