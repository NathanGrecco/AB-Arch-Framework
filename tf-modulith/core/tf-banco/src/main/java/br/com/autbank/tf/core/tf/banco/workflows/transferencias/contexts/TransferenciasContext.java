package br.com.autbank.tf.core.tf.banco.workflows.transferencias.contexts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransferenciasContext {

    Long idRemessa;
    BigDecimal valor;
    String titularDeb;
    String nroContaDeb;
    String titularCred;
    String nroContaCred;
}
