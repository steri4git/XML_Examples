package de.steri.xml.jaxb;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.steri.xml.jaxb.xmlprocessor.JaxbWriter;

public class JaxbWriterTest2 {

	@Test
	public void testWriteXMLFiles() throws JAXBException {
		JaxbWriter writer = new JaxbWriter();
		writer.writeXMLFiles(10);
	}
}
