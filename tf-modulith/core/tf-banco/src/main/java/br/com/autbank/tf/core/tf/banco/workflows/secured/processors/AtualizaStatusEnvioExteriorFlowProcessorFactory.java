package br.com.autbank.tf.core.tf.banco.workflows.secured.processors;

import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import br.com.autbank.tf.core.tf.banco.workflows.secured.items.atualizaEnvioExterior.AtualizaStatusEnvioExteriorFlowItem;
import lombok.AllArgsConstructor;

@Factory
@AllArgsConstructor
public class AtualizaStatusEnvioExteriorFlowProcessorFactory extends FlowProcessorExecutor<Void, SecuredContext, Void> {

    private final AtualizaStatusEnvioExteriorFlowItem atualizaStatusEnvioExteriorFlowItem;

    @Override
    protected FlowProcessor<Void, SecuredContext, Void> buildFlowProcessor() {
        return new FlowBuilder<SecuredContext>().step(atualizaStatusEnvioExteriorFlowItem).build();
    }
}
