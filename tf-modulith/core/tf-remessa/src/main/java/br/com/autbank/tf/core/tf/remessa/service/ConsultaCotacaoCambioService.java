package br.com.autbank.tf.core.tf.remessa.service;

import arch.cache.aop.CacheKey;
import arch.cache.aop.CacheLocal;
import arch.cache.aop.CacheSharedGet;
import arch.cache.aop.CacheSharedPut;
import br.com.autbank.tf.tf.cotacao.cambio.soap.CotacoesCambioPort;
import br.com.autbank.tf.tf.cotacao.cambio.soap.GetTaxaCambioRequest;
import br.com.autbank.tf.tf.cotacao.cambio.soap.Moedas;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Named
@AllArgsConstructor
public class ConsultaCotacaoCambioService {

    private final CotacoesCambioPort cotacoesCambioPort;

    @CacheKey("DM_HISTORICO_COTACAO_%s")
    @CacheSharedGet
    @CacheLocal(ttl = "5m")
    @CacheSharedPut(ttl = "10m")
    public BigDecimal consultaCotacao(Moedas moedas) {
        GetTaxaCambioRequest request = new GetTaxaCambioRequest();
        request.setMoeda(moedas);
        var response = cotacoesCambioPort.getTaxaCambio(request);
        var taxa = BigDecimal.valueOf(Double.parseDouble(response.getTaxaCambio().replace(",", ".")));
        return taxa;
    }
}
