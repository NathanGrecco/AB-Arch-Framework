package br.com.autbank.tf.core.tf.banco.workflows.secured.processors;

import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.banco.models.RetornoEnvioExteriorDto;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import br.com.autbank.tf.core.tf.banco.workflows.secured.items.criacaoEnvioExterior.ConsolidaModelEnvioExteriorFlowItem;
import br.com.autbank.tf.core.tf.banco.workflows.secured.items.criacaoEnvioExterior.ConsolidaRetornoEnvioExteriorDtoFlowItem;
import lombok.AllArgsConstructor;

@Factory
@AllArgsConstructor
public class RegistraEnvioExteriorFlowProcessorFactory extends FlowProcessorExecutor<Void, SecuredContext, RetornoEnvioExteriorDto> {

    private final ConsolidaModelEnvioExteriorFlowItem consolidaModelEnvioExteriorFlowItem;
    private final ConsolidaRetornoEnvioExteriorDtoFlowItem consolidaRetornoEnvioExteriorDtoFlowItem;

    @Override
    protected FlowProcessor<Void, SecuredContext, RetornoEnvioExteriorDto> buildFlowProcessor() {
        return new FlowBuilder<SecuredContext>()
                .step(consolidaModelEnvioExteriorFlowItem)
                .step(consolidaRetornoEnvioExteriorDtoFlowItem)
                .build();
    }
}
