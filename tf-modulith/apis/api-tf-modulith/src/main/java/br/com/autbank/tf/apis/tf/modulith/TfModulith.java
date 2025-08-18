package br.com.autbank.tf.apis.tf.modulith;

import arch.runtime.microservice.Arch;
import arch.runtime.microservice.app.ArchApp;

@ArchApp
public class TfModulith {
	public static void main(String[] args) {
		Arch.run(TfModulith.class, args);
	}
}
