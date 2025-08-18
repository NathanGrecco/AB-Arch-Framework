package br.com.autbank.tf.core.tf.banco.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transferencias {

    private Long id;
    private String numeroContaCredito;
    private BigDecimal valor;
    private Long idRemessa;
    private LocalDateTime dataTransferencia;
}
