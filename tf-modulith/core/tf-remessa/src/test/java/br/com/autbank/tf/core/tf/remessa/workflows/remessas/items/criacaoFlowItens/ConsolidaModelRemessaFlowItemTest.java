package br.com.autbank.tf.core.tf.remessa.workflows.remessas.items.criacaoFlowItens;

import br.com.autbank.tf.core.tf.remessa.model.Remessas;
import br.com.autbank.tf.core.tf.remessa.model.StatusRemessa;
import br.com.autbank.tf.core.tf.remessa.repositories.RemessaRepository;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class ConsolidaModelRemessaFlowItemTest {

    @Mock
    private final RemessaRepository repository;

    @Mock
    private final RemessaContext remessaContext;

    @Test
    void deveCriaraERetornarRemessa() throws Exception {
        BigDecimal valorReais = BigDecimal.valueOf(3500);
        BigDecimal cotacaoDolar = BigDecimal.valueOf(2.5);
        BigDecimal valorDolar = BigDecimal.valueOf(1400);

        var remessaEsperada = Remessas.builder()
                .valorDolar(valorDolar)
                .status(StatusRemessa.CRIADA)
                .cotacaoDolar(cotacaoDolar)
                .valorReais(valorReais)
                .dataStatus(LocalDateTime.now()).build();

        Mockito.when(remessaContext.getTaxaCotacao()).thenReturn(cotacaoDolar);
        Mockito.when(remessaContext.getValor()).thenReturn(valorReais);
        Mockito.when(remessaContext.getValorEnvio()).thenReturn(valorDolar);

        Mockito.verify(repository).registrarRemessa(remessaEsperada);



    }

}