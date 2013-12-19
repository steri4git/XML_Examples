package de.steri.xml.jaxb;

import org.junit.Test;

import de.steri.xml.stax.xmlprocessor.StaxJaxbReader;



/**
 * @author Stefan Rischmueller
 **/
public class StaxJaxbReaderTest {

	@Test
	public void testReadXMLFile() throws Exception {
		StaxJaxbReader reader = new StaxJaxbReader();
		reader.readXMLFile();
	}
}
