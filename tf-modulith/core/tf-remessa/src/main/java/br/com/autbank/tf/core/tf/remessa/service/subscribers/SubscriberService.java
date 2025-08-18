package br.com.autbank.tf.core.tf.remessa.service.subscribers;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.messaging.provider.kafka.subscription.KafkaSubscription;
import arch.messaging.provider.subscriber.SubscriberMessage;
import arch.messaging.provider.subscriber.pipeline.single.SubscriberMessageSingleHandler;
import br.com.autbank.kafka.proto.Protobuf;
import br.com.autbank.tf.core.tf.remessa.service.AtualizaStatusEnvioExterior;
import br.com.autbank.tf.core.tf.remessa.service.AtualizaStatusRemessaService;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;

@Slf4j
@Factory
@AllArgsConstructor
public class SubscriberService {

    private final AtualizaStatusRemessaService atualizaStatusRemessaService;
    private final AtualizaStatusEnvioExterior atualizaStatusEnvioExterior;

    @Bean
    @Singleton
    @Named
    public KafkaSubscription<Protobuf.ProtoRecuperaStatus> provideMySubscription() {

        return KafkaSubscription.builder()
                .pool(Protobuf.ProtoRecuperaStatus.class)
                .from("TF_STATUS_REMESSA")
                .single(message -> {
                    var body = message.bodyValue();
                    Long idRemessa = body.getIdRemessa();
                    String status = body.getStatus();

                    log.info("Mensagem recebida do Kafka: idRemessa={} status={}", idRemessa, status);

                    atualizaStatusRemessaService.atualizaStatusRemessa(idRemessa, status);
                    atualizaStatusEnvioExterior.atualizaStatus(idRemessa);

                })
                .configurer(properties -> {
                    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "recupera-status-consumer-group");
                })
                .build();
    }
}