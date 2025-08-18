package br.com.autbank.tf.core.tf.banco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EnvioExterior {

    private Long id;
    private Long idRemessa;
    private BigDecimal valor;
    private StatusEnvioExterior status;
    private LocalDateTime dataStatus;

    public void setId(Long id) {
        this.id = id;
    }
}
