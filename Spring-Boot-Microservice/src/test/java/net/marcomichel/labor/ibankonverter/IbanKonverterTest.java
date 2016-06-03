package net.marcomichel.labor.ibankonverter;

import junit.framework.TestCase;
import net.marcomichel.labor.ibankonverter.boundary.IbanKonverter;
import net.marcomichel.labor.ibankonverter.control.IbanKonverterImpl;

/**
 * Unit test IBAN Konverter
 */
public class IbanKonverterTest extends TestCase
{
	private IbanKonverter konverter = null;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IbanKonverterTest( String testName )
    {
        super( testName );
        konverter = new IbanKonverterImpl();
    }

    /**
     * Test erfolgreiche Konvertierung
     */
    public void testIbanKonverter()
    {
    	String iban = konverter.berechneIbanDE("456", "123");
        assertEquals("DE654560000000123", iban);
    }

    /**
     * Test Aufruf mit falschen Parametern
     */
    public void testWrongArguments()
    {
    	try {
        	konverter.berechneIbanDE(null, "");
        	fail("Exception wurde nicht geworfen");
		} catch (IllegalArgumentException e) {
		}
    }
}
