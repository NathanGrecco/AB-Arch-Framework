package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.model.Remessas;
import br.com.autbank.tf.core.tf.remessa.models.DadosPagamento;
import br.com.autbank.tf.core.tf.remessa.models.DadosRemessa;
import br.com.autbank.tf.core.tf.remessa.models.RetornoCriacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;

import static br.com.autbank.tf.core.tf.remessa.models.DadosPagamento.NomeBancoEnum.AUTBANK;
import static br.com.autbank.tf.core.tf.remessa.models.DadosPagamento.NroContaBancoEnum._000123_4;

@Named
public class ConsolidaRetornoCriacaoFlowItem extends FlowItem<Remessas, RemessaContext, RetornoCriacaoRemessaDto> {

    @Override
    protected RetornoCriacaoRemessaDto doExecute(Remessas remessas, RemessaContext context) throws Exception {
        var dadosPagamento = DadosPagamento.builder()
                .valor(remessas.getValorReais())
                .nomeBanco(AUTBANK)
                .nroContaBanco(_000123_4)
                .build();
        var dadosRemessa = DadosRemessa.builder()
                .idRemessa(remessas.getId())
                .taxaCambio(remessas.getCotacaoDolar())
                .valorPago(remessas.getValorReais())
                .valorEnvio(remessas.getValorDolar())
                .build();

        return RetornoCriacaoRemessaDto.builder()
                .dadosPagamento(dadosPagamento)
                .dadosRemessa(dadosRemessa)
                .build();
    }
}
