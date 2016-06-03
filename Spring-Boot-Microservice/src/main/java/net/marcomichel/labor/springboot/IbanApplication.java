package net.marcomichel.labor.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.marcomichel.labor.ibankonverter.boundary.IbanKonverter;
import net.marcomichel.labor.ibankonverter.control.IbanKonverterImpl;

/**
 * Startklasse für den IBAN REST Service
 */
@SpringBootApplication
public class IbanApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbanApplication.class, args);
	}

	/**
	 * Konfiguration der Bean mit dem IBAN-Konverter
	 *
	 * @return Instanz der IBAN Konverter Bean
	 */
	@Bean
	public IbanKonverter ibanKonverter() {
		return new IbanKonverterImpl();
	}
}
