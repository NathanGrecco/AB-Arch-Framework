package br.com.autbank.service.workflows.secured.contexts;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BrokerContext {

    Long idRemessa;
    BigDecimal valor;
}
