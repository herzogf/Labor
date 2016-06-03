package net.marcomichel.labor.springboot;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.marcomichel.labor.ibankonverter.boundary.IbanKonverter;

/**
 * IBAN Konverter REST Controller
 */
@RestController
public class IbanController
{
	@Autowired
	private IbanKonverter konverter;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "Hallo, ich bin der IBAN Konverter";
	}

	@RequestMapping(value="/iban", method=RequestMethod.GET)
	public Map<String, String> iban(@RequestParam(value="blz") String blz, @RequestParam(value="kto") String kto) {
		final String iban = konverter.berechneIbanDE(blz, kto);
		return Collections.singletonMap("iban", iban);
	}

}
