package br.com.autbank.tf.core.tf.banco.workflows.transferencias.items;

import arch.common.exception.InternalErrorException;
import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.repositories.ContaRepository;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class ValidaSaldoSuficienteFlowItem extends FlowItem<Void, TransferenciasContext, Void> {

    private final ContaRepository repository;

    @Override
    protected Void doExecute(Void unused, TransferenciasContext context) throws Exception {
        var result = repository.verificaSaldoSuficiente(context.getNroContaDeb(), context.getValor());
        if (!result) {
            throw new InternalErrorException("Saldo insuficiente para transferência");
        }
        return null;
    }
}
