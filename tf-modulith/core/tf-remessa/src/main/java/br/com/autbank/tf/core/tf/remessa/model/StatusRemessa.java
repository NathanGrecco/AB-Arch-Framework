package br.com.autbank.tf.core.tf.remessa.model;

import arch.common.exception.ArchException;
import arch.common.exception.InternalErrorException;
import lombok.Getter;

@Getter
public enum StatusRemessa {

    CRIADA("Criada"),
    PENDENTE("Pendente"),
    PAGA("Paga"),
    ENVIADA("Enviada");

    public String descricao;

    StatusRemessa(String descricao) {
        this.descricao = descricao;
    }

    public static StatusRemessa fromDescricao(String descricao) {
        for (StatusRemessa status : values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new InternalErrorException("StatusRemessa não encontrado para descrição: " + descricao);
    }
}
