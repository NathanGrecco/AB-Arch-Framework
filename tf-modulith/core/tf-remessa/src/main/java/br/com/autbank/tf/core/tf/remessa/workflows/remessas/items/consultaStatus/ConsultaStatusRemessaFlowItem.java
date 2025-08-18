package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.consultaStatus;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.models.RetornoStatusRemessaDto;
import br.com.autbank.tf.core.tf.remessa.repositories.RemessaRepository;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class ConsultaStatusRemessaFlowItem extends FlowItem<Void, RemessaContext, RetornoStatusRemessaDto> {

    private final RemessaRepository repository;

    @Override
    protected RetornoStatusRemessaDto doExecute(Void unused, RemessaContext context) throws Exception {

        var status = repository.recuperaStatusRemessa(context.getId());
        return RetornoStatusRemessaDto.builder()
                .status(status)
                .build();
    }
}
