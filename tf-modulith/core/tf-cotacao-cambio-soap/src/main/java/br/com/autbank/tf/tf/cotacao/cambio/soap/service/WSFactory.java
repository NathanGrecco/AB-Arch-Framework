package br.com.autbank.tf.tf.cotacao.cambio.soap.service;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import br.com.autbank.tf.tf.cotacao.cambio.soap.CotacoesCambioPort;
import br.com.autbank.tf.tf.cotacao.cambio.soap.CotacoesCambioPortService;
import jakarta.inject.Singleton;

@Factory
public class WSFactory {

    @Bean
    @Singleton
    public CotacoesCambioPort providerCotacoesCambioPort() {
        return new CotacoesCambioPortService().getCotacoesCambioPortSoap11();
    }
}
