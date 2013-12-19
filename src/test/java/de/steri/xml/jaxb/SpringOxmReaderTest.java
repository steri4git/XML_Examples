package de.steri.xml.jaxb;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.steri.xml.springoxm.xmlprocessor.SpringOxmReader;
import de.steri.xml.springoxm.xmlprocessor.SpringOxmWriter;

@ContextConfiguration(locations={"/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringOxmReaderTest {
	
	@Autowired
	private SpringOxmReader reader;
	
	@Test
	public void testReadXMLFiles() throws Exception {
       reader.readXML(); 
	}
}



