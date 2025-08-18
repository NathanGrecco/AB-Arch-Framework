package br.com.autbank.tf.core.tf.banco.workflows.transferencias.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.repositories.ContaRepository;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class MovimentaSaldoDasContasFlowItem extends FlowItem<Void, TransferenciasContext, Void> {

    private final ContaRepository contaRepository;

    @Override
    protected Void doExecute(Void unused, TransferenciasContext context) throws Exception {
        contaRepository.adicionaSaldo(context.getNroContaCred(), context.getValor());
        contaRepository.subtraiSaldo(context.getNroContaDeb(), context.getValor());
        return null;
    }
}
