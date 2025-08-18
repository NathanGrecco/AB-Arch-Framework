package br.com.autbank.tf.core.tf.remessa;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RemessaConstants {

    public final static String DOLAR = "USDBRL";
    public final static String CHAMADA_COTACAO = "http://localhost:8089/ws";
    public final static String URI_TF_BANCO = "URI_TF_BANCO";
    public final static String DEFAULT_URI_TF_BANCO = "http://localhost:8941";
    public final static String DEFAULT_URI_TF_BANCO_ENVIO_EXTERIOR = DEFAULT_URI_TF_BANCO + "/secured/envios-exterior";

}
