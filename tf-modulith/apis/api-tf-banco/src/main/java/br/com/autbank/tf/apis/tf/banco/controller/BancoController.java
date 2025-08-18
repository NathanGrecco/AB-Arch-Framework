package br.com.autbank.tf.apis.tf.banco.controller;

import arch.common.http.HttpResponse;
import br.com.autbank.tf.core.tf.banco.controllers.SecuredController;
import br.com.autbank.tf.core.tf.banco.controllers.TransferenciasController;
import br.com.autbank.tf.core.tf.banco.model.StatusEnvioExterior;
import br.com.autbank.tf.core.tf.banco.models.RetornoEnvioExteriorDto;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import br.com.autbank.tf.core.tf.banco.workflows.secured.processors.AtualizaStatusEnvioExteriorFlowProcessorFactory;
import br.com.autbank.tf.core.tf.banco.workflows.secured.processors.RegistraEnvioExteriorFlowProcessorFactory;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts.TransferenciasContext;
import br.com.autbank.tf.core.tf.banco.workflows.transferencias.processors.RegistraTransferenciaFlowProcessorFactory;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class BancoController implements SecuredController, TransferenciasController {

    private final RegistraEnvioExteriorFlowProcessorFactory registraEnvioExteriorFlowProcessorFactory;
    private final RegistraTransferenciaFlowProcessorFactory registraTransferenciaFlowProcessorFactory;
    private final AtualizaStatusEnvioExteriorFlowProcessorFactory atualizaStatusEnvioExteriorFlowProcessorFactory;

    @Override
    public HttpResponse<Void> atualizaStatusTransferencia(AtualizaStatusTransferenciaRequest request) throws Exception {

        var context = SecuredContext.builder()
                .idRemessa(request.getIdRemessa())
                .statusEnvioExterior(StatusEnvioExterior.fromDescricao(request.getBody().getStatus())).build();

        var response = atualizaStatusEnvioExteriorFlowProcessorFactory.execute(context);
        
        return HttpResponse.ok(response);
    }

    @Override
    public HttpResponse<RetornoEnvioExteriorDto> cadastraEnvioExterior(CadastraEnvioExteriorRequest request) throws Exception {

        var context = SecuredContext.builder()
                .idRemessa(request.getBody().getIdRemessa())
                .statusEnvioExterior(StatusEnvioExterior.fromDescricao(request.getBody().getStatus()))
                .valor(request.getBody().getValor())
                .build();

         var response = registraEnvioExteriorFlowProcessorFactory.execute(context);

         return HttpResponse.ok(response);
    }

    @Override
    public HttpResponse<Void> cadastraTransferencia(CadastraTransferenciaRequest request) throws Exception {

        var context = TransferenciasContext.builder()
                .idRemessa(request.getBody().getIdRemessa())
                .valor(request.getBody().getValor())
                .titularCred(request.getBody().getTitularCred())
                .titularDeb(request.getBody().getTitularDeb())
                .nroContaCred(request.getBody().getNroContaCred())
                .nroContaDeb(request.getBody().getNroContaDeb()).build();

        var response = registraTransferenciaFlowProcessorFactory.execute(context);

        return HttpResponse.ok(response);
    }
}
