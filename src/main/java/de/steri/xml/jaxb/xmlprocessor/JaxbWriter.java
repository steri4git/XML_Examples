package de.steri.xml.jaxb.xmlprocessor;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.steri.xml.jaxb.domain.DateiType;
import de.steri.xml.util.StaticObjectFactory;
import de.steri.xml.util.StaticVariables;



/**
 * Some XML-writing-examples using JAXB
 * 
@author Stefan Rischmueller
**/
public class JaxbWriter {
	
	String filePath = StaticVariables.FILE;
	
	/**
	 * Produces ONE big XML-file with a given quanitity of PersonType items
	 * 
	@author Stefan Rischmueller
	@param quanity quantity of XML-items to be created
	**/
	public void writeXMLFile(long quanity) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(DateiType.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(filePath);
		m.marshal(StaticObjectFactory.createDateiType(quanity), file);
	}

	/**
	 * Produces the given quantity of XML-files with each on PersonType item
	 * 
	@author Stefan Rischmueller
	@param quanity quantity of XML-files to be created
	**/
	public void writeXMLFiles(long anzahl) throws JAXBException{
		
		JAXBContext context = JAXBContext.newInstance(DateiType.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		for (long i = 0; i < anzahl; i++) {
			File file = new File( StaticVariables.DIRECTORY_PATH + "/" + StaticVariables.FILE_NAME + i + StaticVariables.FILE_SUFFIX);
			m.marshal(StaticObjectFactory.createDateiType(1), file);
		}
	}
}