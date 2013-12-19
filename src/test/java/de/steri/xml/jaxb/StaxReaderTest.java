package de.steri.xml.jaxb;

import org.junit.Test;

import de.steri.xml.stax.xmlprocessor.StaxReader;



/**
 * @author Stefan Rischmueller
 **/
public class StaxReaderTest {

	@Test
	public void testReadXMLFile() throws Exception {
		StaxReader reader = new StaxReader();
		reader.readXMLFile();
	}
}
