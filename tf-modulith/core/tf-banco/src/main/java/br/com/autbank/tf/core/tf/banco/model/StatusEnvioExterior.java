package br.com.autbank.tf.core.tf.banco.model;

import arch.common.exception.InternalErrorException;
import lombok.Getter;

@Getter
public enum StatusEnvioExterior {

    CRIADA("Criada"),
    PENDENTE("Pendente"),
    PAGA("Paga"),
    LIBERADO("Liberado"),
    ENVIADA("Enviada");

    public String descricao;

    StatusEnvioExterior(String descricao) {
        this.descricao = descricao;
    }

    public static StatusEnvioExterior fromDescricao(String descricao) {
        for (StatusEnvioExterior status : values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new InternalErrorException("StatusRemessa não encontrado para descrição: " + descricao);
    }
}
