package br.com.autbank.tf.core.tf.banco.workflows.secured.contexts;

import br.com.autbank.tf.core.tf.banco.model.StatusEnvioExterior;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class SecuredContext {

    StatusEnvioExterior statusEnvioExterior;
    BigDecimal valor;
    Long idRemessa;
}
