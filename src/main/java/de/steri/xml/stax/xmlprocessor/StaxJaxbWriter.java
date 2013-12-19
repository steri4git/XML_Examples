package de.steri.xml.stax.xmlprocessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import de.steri.xml.jaxb.domain.PersonType;
import de.steri.xml.util.StaticObjectFactory;
import de.steri.xml.util.StaticVariables;

public class StaxJaxbWriter {
	
	private String charEnc ="UTF-8";
	private String directoryPath = StaticVariables.DIRECTORY_PATH;


	public void writeXMLFile(long quantity) throws IOException, Exception {

		OutputStream os = new FileOutputStream(new File(directoryPath + "/dateiaustausch.xml"));
		try {
			XMLStreamWriter xsw = XMLOutputFactory.newInstance()
					.createXMLStreamWriter(new OutputStreamWriter(os, charEnc));
			try {
				Marshaller marshaller = JAXBContext.newInstance(PersonType.class)
						.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_ENCODING, charEnc);
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				xsw.writeStartDocument(charEnc, null);
				xsw.writeCharacters("\n");
				xsw.writeStartElement("root");
				xsw.writeStartElement("name");
				xsw.writeCharacters("Stefan");
				xsw.writeEndElement();
				xsw.writeStartElement("personen");
				xsw.setDefaultNamespace("http://www.steri.de/xml/jaxb");
				xsw.writeDefaultNamespace("http://www.steri.de/xml/jaxb");
				xsw.writeNamespace("xsi",
						"http://www.w3.org/2001/XMLSchema-instance");
				//xsw.writeAttribute("xsi:schemaLocation",
				//		"http://meinnamespace.meinefirma.de Buecher.xsd");
				xsw.writeCharacters("\n");

				
				for (int i = 0; i < quantity; i++) {

					marshaller.marshal(StaticObjectFactory.createPersonType(), xsw);
					xsw.writeCharacters("\n");
				}
				xsw.writeEndElement();
				xsw.writeEndElement();				
				xsw.writeEndDocument();
				xsw.writeCharacters("\n");
				xsw.flush();
			} finally {
				xsw.close();
			}
		} finally {
			os.close();
		}
	}
}
