package br.com.autbank.tf.apis.tf.remessa.controller;

import arch.common.http.HttpResponse;
import br.com.autbank.tf.core.tf.remessa.controllers.RemessasController;
import br.com.autbank.tf.core.tf.remessa.models.RetornoCriacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.models.RetornoSimulacaoRemessaDto;
import br.com.autbank.tf.core.tf.remessa.models.RetornoStatusRemessaDto;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors.RemessaConsultaStatusFlowProcessorFactory;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors.RemessaCriacaoFlowProcessorFactory;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.processors.RemessaSimulacaoFlowProcessorFactory;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class RemessaController implements RemessasController {

    private final RemessaSimulacaoFlowProcessorFactory remessaSimulacaoFlowProcessorFactory;
    private final RemessaCriacaoFlowProcessorFactory remessaCriacaoFlowProcessorFactory;
    private final RemessaConsultaStatusFlowProcessorFactory remessaConsultaStatusFlowProcessorFactory;

    @Override
    public HttpResponse<RetornoCriacaoRemessaDto> criarRemessa(CriarRemessaRequest request) throws Exception {

        var context = RemessaContext.builder()
                .valor(request.getBody().getValor())
                .build();

        var response = remessaCriacaoFlowProcessorFactory.execute(context);

        return HttpResponse.ok(response);
    }

    @Override
    public HttpResponse<RetornoStatusRemessaDto> retornaStatusRemessaPorId(RetornaStatusRemessaPorIdRequest request) throws Exception {

        var context = RemessaContext.builder()
                .id(request.getIdRemessa())
                .build();

        var response = remessaConsultaStatusFlowProcessorFactory.execute(context);

        return HttpResponse.ok(response);
    }

    @Override
    public HttpResponse<RetornoSimulacaoRemessaDto> simularRemessa(SimularRemessaRequest request) throws Exception {
        var context = RemessaContext.builder()
                .valor(request.getValor())
                .build();

        var response = remessaSimulacaoFlowProcessorFactory.execute(context);

        return HttpResponse.ok(response);
    }

}
