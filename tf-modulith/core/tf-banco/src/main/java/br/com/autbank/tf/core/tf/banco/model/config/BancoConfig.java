package br.com.autbank.tf.core.tf.banco.model.config;

import arch.common.config.Config;
import jakarta.inject.Named;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;

import static br.com.autbank.tf.core.tf.banco.BancoConstants.BROKER_URI;
import static br.com.autbank.tf.core.tf.banco.BancoConstants.DEFAULT_BROKER_URI;

@Named
@Getter
public class BancoConfig {

    private final URI uriBroker;

    public BancoConfig(Config config) throws URISyntaxException {
        this.uriBroker = config.getOptionalValue(BROKER_URI, URI.class).orElse(new URI(DEFAULT_BROKER_URI));
    }
}
