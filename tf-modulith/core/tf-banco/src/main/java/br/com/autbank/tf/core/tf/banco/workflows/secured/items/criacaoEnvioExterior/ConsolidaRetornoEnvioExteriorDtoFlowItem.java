package br.com.autbank.tf.core.tf.banco.workflows.secured.items.criacaoEnvioExterior;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.tf.core.tf.banco.model.EnvioExterior;
import br.com.autbank.tf.core.tf.banco.models.RetornoEnvioExteriorDto;
import br.com.autbank.tf.core.tf.banco.workflows.secured.contexts.SecuredContext;
import jakarta.inject.Named;

@Named
public class ConsolidaRetornoEnvioExteriorDtoFlowItem extends FlowItem<EnvioExterior, SecuredContext, RetornoEnvioExteriorDto> {

    @Override
    protected RetornoEnvioExteriorDto doExecute(EnvioExterior envioExterior, SecuredContext context) throws Exception {
        return RetornoEnvioExteriorDto.builder()
                .valor(envioExterior.getValor())
                .status(envioExterior.getStatus().getDescricao())
                .idRemessa(envioExterior.getIdRemessa())
                .build();
    }
}
