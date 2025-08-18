package br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors;

import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.remessa.models.RetornoStatusRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.consultaStatus.ConsultaExistenciaDaRemessaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.consultaStatus.ConsultaStatusRemessaFlowItem;
import lombok.AllArgsConstructor;


@Factory
@AllArgsConstructor
public class RemessaConsultaStatusFlowProcessorFactory extends FlowProcessorExecutor<Void, RemessaContext, RetornoStatusRemessaDto> {

    private final ConsultaStatusRemessaFlowItem consultaStatusRemessaFlowItem;
    private final ConsultaExistenciaDaRemessaFlowItem consultaExistenciaDaRemessaFlowItem;

    @Override
    protected FlowProcessor<Void, RemessaContext, RetornoStatusRemessaDto> buildFlowProcessor() {
        return new FlowBuilder<RemessaContext>()
                .step(consultaExistenciaDaRemessaFlowItem)
                .step(consultaStatusRemessaFlowItem)
                .build();
    }
}
