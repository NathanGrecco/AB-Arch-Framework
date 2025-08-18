package br.com.autbank.tf.core.tf.remessa.service;

import br.com.autbank.tf.core.tf.remessa.model.StatusRemessa;
import br.com.autbank.tf.core.tf.remessa.repositories.RemessaRepository;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class AtualizaStatusRemessaService {

    private final RemessaRepository repository;

    public void atualizaStatusRemessa(Long idRemessa, String status) {
        var statusRemessa = StatusRemessa.fromDescricao(status);
        repository.atualizaStatusRemessa(statusRemessa, idRemessa);
    }
}
