package br.com.autbank.tf.core.tf.banco.workflows.transferencias.processors;

import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.items.MovimentaSaldoDasContasFlowItem;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.items.PublicaStatusRemessaKafkaFlowItem;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.items.RegistraTransferenciaBancariaFlowItem;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.items.ValidaSaldoSuficienteFlowItem;
import lombok.AllArgsConstructor;

@Factory
@AllArgsConstructor
public class RegistraTransferenciaFlowProcessorFactory extends FlowProcessorExecutor<Void, TransferenciasContext, Void> {

    private final MovimentaSaldoDasContasFlowItem movimentaSaldoDasContasFlowItem;
    private final RegistraTransferenciaBancariaFlowItem registraTransferenciaBancariaFlowItem;
    private final ValidaSaldoSuficienteFlowItem validaSaldoSuficienteFlowItem;
    private final PublicaStatusRemessaKafkaFlowItem publicaStatusRemessaKafkaFlowItem;

    @Override
    protected FlowProcessor<Void, TransferenciasContext, Void> buildFlowProcessor() {
        return new FlowBuilder<TransferenciasContext>()
                .step(validaSaldoSuficienteFlowItem)
                .step(registraTransferenciaBancariaFlowItem)
                .step(movimentaSaldoDasContasFlowItem)
                .step(publicaStatusRemessaKafkaFlowItem)
                .build();
    }

}
