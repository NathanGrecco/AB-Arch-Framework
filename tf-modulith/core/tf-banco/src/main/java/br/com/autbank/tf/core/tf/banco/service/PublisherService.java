package br.com.autbank.tf.core.tf.banco.service;

import arch.messaging.provider.kafka.Kafka;
import arch.messaging.provider.publisher.Publisher;
import arch.messaging.provider.publisher.PublisherMessage;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import br.com.autbank.kafka.proto.Protobuf;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@Named
@RequiredArgsConstructor
public class PublisherService {

    @Kafka
    private final Publisher publisher;

    public void enviarStatus(Long idRemessa, String status) throws ExecutionException, InterruptedException {
        var protoMsg = Protobuf.ProtoRecuperaStatus.newBuilder()
                .setIdRemessa(idRemessa)
                .setStatus(status)
                .build();

        var message = PublisherMessage.builder()
                .body(protoMsg)
                .header("idRemessa", idRemessa)
                .build();

        publisher.publishAsync("TF_STATUS_REMESSA", message).get();
    }

}
