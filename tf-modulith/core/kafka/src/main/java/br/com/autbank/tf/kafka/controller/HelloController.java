package br.com.autbank.tf.kafka.controller;
import arch.common.http.HttpResponse;
import jakarta.inject.Named;
import br.com.autbank.tf.kafka.controllers.HelloKafkaController;
import br.com.autbank.tf.kafka.models.Hello;
@Named
public class HelloController implements HelloKafkaController {
    @Override
    public HttpResponse<Hello> hello(HelloRequest request) throws Exception {
        return HttpResponse.ok(new Hello("kafka!!!"));
    }
}