package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.consultaStatus;

import arch.common.exception.NotFoundException;
import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.repositories.RemessaRepository;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class ConsultaExistenciaDaRemessaFlowItem extends FlowItem<Void, RemessaContext, Void>  {

    private final RemessaRepository repository;

    @Override
    protected Void doExecute(Void unused, RemessaContext context) throws Exception {
        boolean existe = repository.existeRemessa(context.getId());

        if (!existe) {
            throw new NotFoundException("Remessa do id: " + context.getId() + " inexistente");
        }
        return null;
    }
}
