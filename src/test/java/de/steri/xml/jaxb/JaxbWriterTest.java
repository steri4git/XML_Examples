package de.steri.xml.jaxb;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.steri.xml.jaxb.xmlprocessor.JaxbWriter;

public class JaxbWriterTest {

	@Test
	public void testWriteXMLFile() throws JAXBException {
		JaxbWriter writer = new JaxbWriter();
		writer.writeXMLFile(1);
	}
}
