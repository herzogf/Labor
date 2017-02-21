package net.marcomichel.labor.ibankonverter.boundary;

/**
 * Boundary des Iban Konverters
 */
public interface IbanKonverter {

	/**
	 * Konvertiert BLZ und Kontonummer in eine deutsche IBAN
	 *
	 * @param blz Bankleitzahl
	 * @param kto Kontonummer
	 * @return IBAN
	 */
	public String berechneIbanDE(String blz, String kto);
}
