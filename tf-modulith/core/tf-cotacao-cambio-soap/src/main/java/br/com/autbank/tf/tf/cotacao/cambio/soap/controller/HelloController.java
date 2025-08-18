package br.com.autbank.tf.tf.cotacao.cambio.soap.controller;
import arch.common.http.HttpResponse;
import jakarta.inject.Named;
import br.com.autbank.tf.tf.cotacao.cambio.soap.controllers.HelloTfCotacaoCambioSoapController;
import br.com.autbank.tf.tf.cotacao.cambio.soap.models.Hello;
@Named
public class HelloController implements HelloTfCotacaoCambioSoapController {
    @Override
    public HttpResponse<Hello> hello(HelloRequest request) throws Exception {
        return HttpResponse.ok(new Hello("tf-cotacao-cambio-soap!!!"));
    }
}