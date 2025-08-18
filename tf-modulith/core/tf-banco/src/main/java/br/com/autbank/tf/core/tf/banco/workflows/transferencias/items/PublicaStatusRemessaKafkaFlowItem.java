package br.com.autbank.tf.core.tf.banco.workflows.transferencias.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.service.PublisherService;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class PublicaStatusRemessaKafkaFlowItem extends FlowItem<Void, TransferenciasContext, Void> {

    private final PublisherService publisherService;
    private final static String STATUS_PAGA = "PAGA";

    @Override
    protected Void doExecute(Void unused, TransferenciasContext context) throws Exception {
        publisherService.enviarStatus(context.getIdRemessa(), STATUS_PAGA);
        return null;
    }
}
