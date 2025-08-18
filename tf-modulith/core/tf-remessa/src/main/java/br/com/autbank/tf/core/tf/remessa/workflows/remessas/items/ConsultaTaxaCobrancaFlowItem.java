package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.service.ConsultaCotacaoCambioService;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import br.com.autbank.tf.tf.cotacao.cambio.soap.Moedas;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@AllArgsConstructor
public class ConsultaTaxaCobrancaFlowItem extends FlowItem<Void, RemessaContext, Void> {

    private final ConsultaCotacaoCambioService service;

    @Override
    protected Void doExecute(Void unused, RemessaContext context) throws Exception {
        context.setTaxaCotacao(service.consultaCotacao(Moedas.USDBRL));
        return null;
    }
}
