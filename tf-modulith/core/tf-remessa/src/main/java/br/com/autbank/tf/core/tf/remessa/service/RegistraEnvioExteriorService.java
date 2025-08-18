package br.com.autbank.tf.core.tf.remessa.service;

import arch.common.exception.InternalErrorException;
import arch.common.http.body.HttpBodyHandlers;
import arch.common.http.body.HttpBodyPublishers;
import arch.common.oauth2.OAuth2Client;
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
public class RegistraEnvioExteriorService {

    private final HttpClient httpClient;
    private final OAuth2Client oAuth2Client;

    public void enviaDadosParaRegistroEnvioExterior(RemessaContext remessaContext, URI uri) throws IOException, InterruptedException {
        var requestObj = EnvioExteriorRequest.builder()
                .valor(remessaContext.getValorEnvio())
                .status("Pendente")
                .idRemessa(remessaContext.getId())
                .build();

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("content-type", "application/json")
                .header("authorization", String.format("Bearer %s", oAuth2Client.getAccessToken()))
                .POST(HttpBodyPublishers.ofJson(requestObj))
                .build();

        var response = httpClient.send(request, HttpBodyHandlers.ofCodec());
        if (response.statusCode() != 200) {
            log.error("Ocorreu um erro durante a requisição, código http {}", response.statusCode());
            throw new InternalErrorException("Falha na requisição");
        }
    }
}
