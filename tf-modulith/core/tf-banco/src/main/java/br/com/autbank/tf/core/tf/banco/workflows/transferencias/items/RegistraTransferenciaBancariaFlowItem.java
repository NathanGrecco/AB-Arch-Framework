package br.com.autbank.tf.core.tf.banco.workflows.transferencias.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.model.Transferencias;
import br.com.autbank.tf.core.tf.banco.repositories.TransferenciasRepository;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Named
@AllArgsConstructor
public class RegistraTransferenciaBancariaFlowItem extends FlowItem<Void, TransferenciasContext, Void> {

    private final TransferenciasRepository transferenciasRepository;

    @Override
    protected Void doExecute(Void unused, TransferenciasContext context) throws Exception {
        var transferencias = Transferencias.builder()
                .dataTransferencia(LocalDateTime.now())
                .valor(context.getValor())
                .numeroContaCredito(context.getNroContaCred())
                .idRemessa(context.getIdRemessa())
                .build();
        transferenciasRepository.registrarTransferencia(transferencias);
        return null;
    }
}
