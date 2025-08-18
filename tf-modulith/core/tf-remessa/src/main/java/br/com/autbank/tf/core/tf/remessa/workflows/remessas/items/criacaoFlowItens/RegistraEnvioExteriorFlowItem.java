package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.remessa.config.RemessaConfig;
import br.com.autbank.tf.core.tf.remessa.model.Remessas;
import br.com.autbank.tf.core.tf.remessa.service.RegistraEnvioExteriorService;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class RegistraEnvioExteriorFlowItem extends FlowItem<Remessas, RemessaContext, Remessas> {

    private final RegistraEnvioExteriorService registraEnvioExteriorService;
    private final RemessaConfig remessaConfig;

    @Override
    protected Remessas doExecute(Remessas remessas, RemessaContext context) throws Exception {
        registraEnvioExteriorService.enviaDadosParaRegistroEnvioExterior(context, remessaConfig.getUriTfBancoEnvioExterior());
        return remessas;
    }
}
