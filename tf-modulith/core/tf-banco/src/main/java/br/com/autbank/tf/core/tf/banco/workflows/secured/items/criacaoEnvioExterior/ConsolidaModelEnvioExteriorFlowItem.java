package br.com.autbank.tf.core.tf.banco.workflows.secured.items.criacaoEnvioExterior;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.model.EnvioExterior;
import br.com.autbank.tf.core.tf.banco.repositories.EnvioExteriorRepository;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Named
@AllArgsConstructor
public class ConsolidaModelEnvioExteriorFlowItem extends FlowItem<Void, SecuredContext, EnvioExterior> {

    private final EnvioExteriorRepository envioExteriorRepository;

    @Override
    protected EnvioExterior doExecute(Void unused, SecuredContext context) throws Exception {
        var envioExterior = EnvioExterior.builder()
                .valor(context.getValor())
                .status(context.getStatusEnvioExterior())
                .idRemessa(context.getIdRemessa())
                .dataStatus(LocalDateTime.now())
                .build();

        return envioExteriorRepository.registrarEnvioExterior(envioExterior);
    }
}
