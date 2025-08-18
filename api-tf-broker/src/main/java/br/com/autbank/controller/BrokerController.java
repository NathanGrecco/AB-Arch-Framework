package br.com.autbank.controller;

import arch.common.http.HttpResponse;
import br.com.autbank.service.workflows.secured.contexts.BrokerContext;
import br.com.autbank.service.workflows.secured.processors.EnviaDinheiroFlowProcessorFactory;
import core.autogen.controllers.SecuredController;
import core.autogen.models.RetornoEnvioBrokerDto;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@AllArgsConstructor
public class BrokerController implements SecuredController {

    private final EnviaDinheiroFlowProcessorFactory enviaDinheiroFlowProcessorFactory;

    @Override
    public HttpResponse<RetornoEnvioBrokerDto> enviaDinheiro(EnviaDinheiroRequest request) throws Exception {

        var context = BrokerContext.builder()
                .idRemessa(request.getBody().getIdRemessa())
                .valor(request.getBody().getValor())
                .build();

        var response = enviaDinheiroFlowProcessorFactory.execute(context);
        log.info(response.toString());
        return HttpResponse.ok(response);
    }
}
