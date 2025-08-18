package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.simulacaoFlowItens;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.models.RetornoSimulacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;

@Named
public class ConsolidaDadosRetornoSimulacaoFlowItem extends FlowItem<Void, RemessaContext, RetornoSimulacaoRemessaDto> {

    @Override
    protected RetornoSimulacaoRemessaDto doExecute(Void unused, RemessaContext context) throws Exception {
        return RetornoSimulacaoRemessaDto.builder()
                .valor(context.getValor())
                .taxaCambio(context.getTaxaCotacao())
                .valorEnvio(context.getValorEnvio())
                .build();
    }
}
