package net.marcomichel.labor.ibankonverter.control;

import java.math.BigInteger;

import net.marcomichel.labor.ibankonverter.boundary.IbanKonverter;

/**
 * Implementierung des IBAN Konverters
 */
public class IbanKonverterImpl implements IbanKonverter {

	private static final String DE_KZ = "DE";
	private static final int DE_CODE = 1314;
	private static final BigInteger PZ_Mod = new BigInteger("97");
	private static final String IBAN_FORMAT = "%s%02d%s%s";

	public String berechneIbanDE(String blz, String kto) {
		if (kto == null || kto.trim().length() == 0 || blz == null || blz.trim().length() == 0) {
			throw new IllegalArgumentException("Bankleitzahl und Kontonummer sind verpflichtend");
		}

		// Kontonummer auf 10 Stellen mit führenden Nullen auffüllen
		final String m_kto = String.format("%010d", new BigInteger(kto));

		// Prüfsumme bilden
		StringBuilder pruefsumme = new StringBuilder();
		pruefsumme.append(blz)
		          .append(m_kto)
		          .append(DE_CODE)
		          .append("00");

		final BigInteger bi = new BigInteger(pruefsumme.toString());

		// Prüfziffer berechnen: 98 - Prüfsumme MOD 97
		final int pZiffer = 98 - bi.mod(PZ_Mod).intValue();

		// IBAN Formatieren
		final String iban = String.format(IBAN_FORMAT, DE_KZ, pZiffer, blz, m_kto);
		return iban;
	}

}
