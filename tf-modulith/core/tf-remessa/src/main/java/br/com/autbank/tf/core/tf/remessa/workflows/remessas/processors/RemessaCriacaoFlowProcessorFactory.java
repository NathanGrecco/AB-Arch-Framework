package br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.remessa.models.RetornoCriacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.CalculaValorEnvioAposTaxaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.ConsultaTaxaCobrancaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.ValidaValorRecebidoFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens.ConsolidaModelRemessaFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens.ConsolidaRetornoCriacaoFlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens.RegistraEnvioExteriorFlowItem;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

@Factory
@AllArgsConstructor
public class RemessaCriacaoFlowProcessorFactory extends FlowProcessorExecutor<Void, RemessaContext, RetornoCriacaoRemessaDto> {

    private final ValidaValorRecebidoFlowItem validaValorRecebidoFlowItem;
    private final ConsultaTaxaCobrancaFlowItem consultaTaxaCobrancaFlowItem;
    private final CalculaValorEnvioAposTaxaFlowItem calculaValorEnvioAposTaxaFlowItem;
    private final ConsolidaModelRemessaFlowItem consolidaModelRemessaFlowItem;
    private final ConsolidaRetornoCriacaoFlowItem consolidaRetornoCriacaoFlowItem;
    private final RegistraEnvioExteriorFlowItem registraEnvioExteriorFlowItem;

    @Bean
    @Singleton
    @Override
    public FlowProcessor<Void, RemessaContext, RetornoCriacaoRemessaDto> buildFlowProcessor() {
        return new FlowBuilder<RemessaContext>()
                .step(validaValorRecebidoFlowItem)
                .step(consultaTaxaCobrancaFlowItem)
                .step(calculaValorEnvioAposTaxaFlowItem)
                .step(consolidaModelRemessaFlowItem)
                .step(registraEnvioExteriorFlowItem)
                .step(consolidaRetornoCriacaoFlowItem)
                .build();
    }

}
