package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;

import java.math.RoundingMode;

@Named
public class CalculaValorEnvioAposTaxaFlowItem extends FlowItem<Void, RemessaContext, Void> {

    @Override
    protected Void doExecute(Void unused, RemessaContext context) throws Exception {
        var taxa = context.getTaxaCotacao();
        var valor = context.getValor();
        var valorEnvio = valor.divide(taxa, 2 , RoundingMode.HALF_UP);
        context.setValorEnvio(valorEnvio);
        return null;
    }
}
