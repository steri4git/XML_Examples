package de.steri.xml.jaxb;

import org.junit.Test;

import de.steri.xml.stax.xmlprocessor.StaxJaxbWriter;


/**
@author Stefan Rischmueller
**/
public class StaxJaxbWriterTest {

	@Test
	public void testWriteXMLFile() throws Exception {
		StaxJaxbWriter writer = new StaxJaxbWriter();
		writer.writeXMLFile(10);
	}
}
