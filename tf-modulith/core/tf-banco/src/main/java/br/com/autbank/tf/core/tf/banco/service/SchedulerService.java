package br.com.autbank.tf.core.tf.banco.service;

import arch.runtime.annotation.bridge.scheduling.TaskScheduler;
import br.com.autbank.tf.core.tf.banco.model.EnvioExterior;
import br.com.autbank.tf.core.tf.banco.model.StatusEnvioExterior;
import br.com.autbank.tf.core.tf.banco.repositories.EnvioExteriorRepository;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Named
@AllArgsConstructor
public class SchedulerService {

    private final EnviaDinheiroAoBrokerService enviaDinheiroAoBrokerService;
    private final EnvioExteriorRepository envioExteriorRepository;
    private final PublisherService publisherService;

    @TaskScheduler(cron = "*/1 * * * *")
    public void lidaComEnvioExterior() {
        List<EnvioExterior> envioExteriorList = buscaEnvioExteriorLiberado();
        log.info("[SCHEDULER] - Inicio: {}", LocalDateTime.now());
        envioExteriorList.forEach(i -> enviaDinheiroAoBroker(i.getIdRemessa(), i.getValor()));
        log.info("[SCHEDULER] - Dinheiro Enviado ao broker: {}", LocalDateTime.now());
        envioExteriorList.forEach(i -> enviaStatusKafka(i.getIdRemessa()));
        log.info("[SCHEDULER] - Status enviado ao tópico: {}", LocalDateTime.now());
    }

    private void enviaStatusKafka(Long idRemessa) {
        try {
            publisherService.enviarStatus(idRemessa, "Enviada");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void alteraStatusEnvioExteriorProcessado(Long idRemessa) {
        envioExteriorRepository.atualizaStatusEnviosExterior(StatusEnvioExterior.ENVIADA, idRemessa);
    }

    private List<EnvioExterior> buscaEnvioExteriorLiberado() {
        var result = envioExteriorRepository.listaRegistrosLiberado(StatusEnvioExterior.LIBERADO);
        if (result.isEmpty()) {
            throw new RuntimeException("[SCHEDULER] - Não existe nenhum EnvioExterior liberado para processamento");
        }
        return result;
    }

    private void enviaDinheiroAoBroker(Long idRemessa, BigDecimal valor) {
        try {
            enviaDinheiroAoBrokerService.enviar(idRemessa, valor);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
