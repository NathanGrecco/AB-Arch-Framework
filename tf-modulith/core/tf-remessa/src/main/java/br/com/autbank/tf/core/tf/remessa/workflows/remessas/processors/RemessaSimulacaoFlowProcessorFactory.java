package br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.remessa.models.RetornoSimulacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.CalculaValorEnvioAposTaxaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.ConsultaTaxaCobrancaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.ValidaValorRecebidoFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.simulacaoFlowItens.ConsolidaDadosRetornoSimulacaoFlowItem;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;


@Factory
@AllArgsConstructor
public class RemessaSimulacaoFlowProcessorFactory extends FlowProcessorExecutor<Void, RemessaContext, RetornoSimulacaoRemessaDto> {

    public final ValidaValorRecebidoFlowItem validaValorRecebidoFlowItem;
    public final ConsultaTaxaCobrancaFlowItem consultaTaxaCobrancaFlowItem;
    public final ConsolidaDadosRetornoSimulacaoFlowItem consolidaDadosRetornoSimulacaoFlowItem;
    public final CalculaValorEnvioAposTaxaFlowItem calculaValorEnvioAposTaxaFlowItem;

    @Bean
    @Singleton
    @Override
    public FlowProcessor<Void, RemessaContext, RetornoSimulacaoRemessaDto> buildFlowProcessor() {
        return new FlowBuilder<RemessaContext>()
                .step(validaValorRecebidoFlowItem)
                .step(consultaTaxaCobrancaFlowItem)
                .step(calculaValorEnvioAposTaxaFlowItem)
                .step(consolidaDadosRetornoSimulacaoFlowItem)
                .build();

    }
}
