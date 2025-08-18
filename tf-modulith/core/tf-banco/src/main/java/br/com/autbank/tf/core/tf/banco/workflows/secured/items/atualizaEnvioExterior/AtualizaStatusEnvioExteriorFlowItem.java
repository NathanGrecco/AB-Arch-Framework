package br.com.autbank.tf.core.tf.banco.workflows.secured.items.atualizaEnvioExterior;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.repositories.EnvioExteriorRepository;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class AtualizaStatusEnvioExteriorFlowItem extends FlowItem<Void, SecuredContext, Void> {

    private final EnvioExteriorRepository envioExteriorRepository;

    @Override
    protected Void doExecute(Void unused, SecuredContext context) throws Exception {
        envioExteriorRepository.atualizaStatusEnviosExterior(context.getStatusEnvioExterior(), context.getIdRemessa());
        return null;
    }
}
