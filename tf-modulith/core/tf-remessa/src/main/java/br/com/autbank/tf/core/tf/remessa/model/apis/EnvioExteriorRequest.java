package br.com.autbank.tf.core.tf.remessa.model.apis;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnvioExteriorRequest {

    private Long idRemessa;
    private BigDecimal valor;
    private String status;
}
