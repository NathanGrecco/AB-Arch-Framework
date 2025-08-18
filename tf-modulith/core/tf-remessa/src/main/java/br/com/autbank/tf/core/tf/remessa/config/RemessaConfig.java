package br.com.autbank.tf.core.tf.remessa.config;

import arch.common.config.Config;
import jakarta.inject.Named;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;

import static br.com.autbank.tf.core.tf.remessa.RemessaConstants.*;

@Named
@Getter
public class RemessaConfig {

    private final URI uriTfBancoEnvioExterior;

    public RemessaConfig(Config config) throws URISyntaxException {
        this.uriTfBancoEnvioExterior = config.getOptionalValue(URI_TF_BANCO, URI.class).orElse(new URI(DEFAULT_URI_TF_BANCO_ENVIO_EXTERIOR));
    }
}
