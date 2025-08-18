package br.com.autbank.service.workflows.secured.processors;

import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import arch.pattern.workflow2.flow.FlowProcessorExecutor;
import br.com.autbank.service.workflows.secured.contexts.BrokerContext;
import br.com.autbank.service.workflows.secured.items.EnviaDinheiroFlowItem;
import core.autogen.models.RetornoEnvioBrokerDto;
import lombok.AllArgsConstructor;

@Factory
@AllArgsConstructor
public class EnviaDinheiroFlowProcessorFactory extends FlowProcessorExecutor<Void, BrokerContext, RetornoEnvioBrokerDto> {


    private final EnviaDinheiroFlowItem enviaDinheiroFlowItem;

    @Override
    protected FlowProcessor<Void, BrokerContext, RetornoEnvioBrokerDto> buildFlowProcessor() {
        return new FlowBuilder<BrokerContext>()
                .step(enviaDinheiroFlowItem)
                .build();
    }
}
