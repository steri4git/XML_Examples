package de.steri.xml.jaxb;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.xml.sax.SAXException;

import de.steri.xml.jaxb.xmlprocessor.JaxbReader;
import de.steri.xml.jaxb.xmlprocessor.JaxbWriter;

public class JaxbReaderTest {

	@Test
	public void testReadXMLFile() throws JAXBException, SAXException {
		JaxbReader reader = new JaxbReader();
		reader.readXMLFile();
	}

}
