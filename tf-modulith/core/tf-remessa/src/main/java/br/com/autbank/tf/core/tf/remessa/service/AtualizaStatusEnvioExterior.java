package br.com.autbank.tf.core.tf.remessa.service;

import arch.common.exception.InternalErrorException;
import arch.common.http.body.HttpBodyHandlers;
import arch.common.http.body.HttpBodyPublishers;
import arch.common.oauth2.OAuth2Client;
import br.com.autbank.tf.core.tf.remessa.config.RemessaConfig;
import br.com.autbank.tf.core.tf.remessa.model.apis.EnvioExteriorRequest;
import br.com.autbank.tf.core.tf.remessa.workflows.remessas.context.RemessaContext;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Named
@Slf4j
@AllArgsConstructor
public class AtualizaStatusEnvioExterior {

    private final HttpClient httpClient;
    private final OAuth2Client oAuth2Client;
    private final RemessaConfig remessaConfig;

    public void atualizaStatus(Long idRemessa) throws IOException, InterruptedException {

        URI uri = URI.create(remessaConfig.getUriTfBancoEnvioExterior() + "/" + idRemessa);
        var requestObj = EnvioExteriorRequest.builder()
                .status("Liberado")
                .build();

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("content-type", "application/json")
                .header("authorization", String.format("Bearer %s", oAuth2Client.getAccessToken()))
                .method("PATCH", HttpBodyPublishers.ofJson(requestObj))
                .build();

        var response = httpClient.send(request, HttpBodyHandlers.ofCodec());

        if (response.statusCode() != 200) {
            log.error("Ocorreu um erro durante a requisição, código http {}", response.statusCode());
            throw new InternalErrorException("Falha na requisição");
        }
    }
}
