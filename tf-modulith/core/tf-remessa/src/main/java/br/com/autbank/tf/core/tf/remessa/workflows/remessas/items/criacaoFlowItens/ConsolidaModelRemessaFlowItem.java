package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.model.Remessas;
import br.com.autbank.tf.core.tf.remessa.model.StatusRemessa;
import br.com.autbank.tf.core.tf.remessa.repositories.RemessaRepository;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Named
@AllArgsConstructor
public class ConsolidaModelRemessaFlowItem extends FlowItem<Void, RemessaContext, Remessas> {

    private final RemessaRepository repository;

    @Override
    protected Remessas doExecute(Void unused, RemessaContext context) throws Exception {
        var remessas = Remessas.builder()
                .valorReais(context.getValor())
                .valorDolar(context.getValorEnvio())
                .cotacaoDolar(context.getTaxaCotacao())
                .status(StatusRemessa.CRIADA)
                .dataStatus(LocalDateTime.now())
                .build();

        var remessaCriada = repository.registrarRemessa(remessas);
        context.setId(remessas.getId());

        return remessaCriada;
    }
}
