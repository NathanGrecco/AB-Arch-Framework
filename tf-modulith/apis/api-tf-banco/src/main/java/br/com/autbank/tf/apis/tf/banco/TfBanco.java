package br.com.autbank.tf.apis.tf.banco;

import arch.runtime.microservice.Arch;
import arch.runtime.microservice.app.ArchApp;

@ArchApp
public class TfBanco {
	public static void main(String[] args) {
		Arch.run(TfBanco.class, args);
	}
}
