package br.com.autbank.tf.core.tf.remessa.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor@NoArgsConstructor
public class Remessas {

    private Long id;
    private BigDecimal valorReais;
    private BigDecimal valorDolar;
    private BigDecimal cotacaoDolar;
    private StatusRemessa status;
    private LocalDateTime dataStatus;

    public void setId(Long id) {
        this.id = id;
    }
}
