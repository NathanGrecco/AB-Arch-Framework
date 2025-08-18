package br.com.autbank.tf.apis.tf.remessa;

import arch.runtime.microservice.Arch;
import arch.runtime.microservice.app.ArchApp;

@ArchApp
public class TfRemessa {
	public static void main(String[] args) {
		Arch.run(TfRemessa.class, args);
	}
}
