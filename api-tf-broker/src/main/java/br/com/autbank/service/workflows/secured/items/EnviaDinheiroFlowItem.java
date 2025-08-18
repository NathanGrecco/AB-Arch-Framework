package br.com.autbank.service.workflows.secured.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.service.workflows.secured.contexts.BrokerContext;
import core.autogen.models.RetornoEnvioBrokerDto;
import jakarta.inject.Named;

@Named
public class EnviaDinheiroFlowItem extends FlowItem<Void, BrokerContext, RetornoEnvioBrokerDto> {


    @Override
    protected RetornoEnvioBrokerDto doExecute(Void unused, BrokerContext context) throws Exception {
        return RetornoEnvioBrokerDto.builder()
                .idRemessa(context.getIdRemessa())
                .valor(context.getValor()).build();
    }
}
