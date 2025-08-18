package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.exceptions.GenericExceptions;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;

import java.math.BigDecimal;

@Named
public class ValidaValorRecebidoFlowItem extends FlowItem<Void, RemessaContext, Void> {

    @Override
    protected Void doExecute(Void unused, RemessaContext context) throws Exception {
         if (context.getValor().compareTo(BigDecimal.ZERO) < 0) {
             throw new GenericExceptions("Valor da cotação não pode ser meno que zero");
         }
         return null;
    }
}
