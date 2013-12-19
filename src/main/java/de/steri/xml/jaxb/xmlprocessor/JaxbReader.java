package de.steri.xml.jaxb.xmlprocessor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import de.steri.xml.jaxb.domain.DateiType;
import de.steri.xml.util.StaticVariables;

public class JaxbReader {

	String filePath = StaticVariables.FILE;
	String schemaPath = StaticVariables.SCHEMA_PATH;

	/**
	 * Produces ONE big XML-file with a given quanitity of PersonType items
	 * 
	 * @author Stefan Rischmueller
	 * @param quanity
	 *            quantity of XML-items to be created
	 * @throws SAXException 
	 **/
	public void readXMLFile() throws JAXBException, SAXException {

		File file = new File(filePath);
		JAXBContext context = JAXBContext.newInstance(DateiType.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Schemavalidierung auskommentiert, um eine Vergleichbarkeit zu anderen Implementierungen zu schaffen.
		//SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		//Schema schema = sf.newSchema(new File(schemaPath));
		//unmarshaller.setSchema(schema);
		DateiType datei = (DateiType) unmarshaller.unmarshal(file);
		System.out.println(datei.getPersonen().size());
	}
	
	
}
