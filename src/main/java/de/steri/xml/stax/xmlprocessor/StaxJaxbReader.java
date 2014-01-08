package de.steri.xml.stax.xmlprocessor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import de.steri.xml.jaxb.domain.PersonType;
import de.steri.xml.util.StaticVariables;

public class StaxJaxbReader {

	private String charEnc = "UTF-8";
	private String directoryPath = StaticVariables.DIRECTORY_PATH;

	/**
	 * Interface fuer Callback-Klassen fuer die Verarbeitung der einzelnen
	 * XML-Elemente.
	 */
	public interface ElementeVerarbeitung {
		void verarbeite(Object element);
	}

	public void readXMLFile() throws IOException, Exception {
		List<PersonType> liste = new ArrayList<PersonType>();
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		FileReader fr = new FileReader(StaticVariables.FILE);
		XMLEventReader xmler = xmlif.createXMLEventReader(fr);
		EventFilter filter = new EventFilter() {
			public boolean accept(XMLEvent event) {
				return event.isStartElement();
			}
		};
		XMLEventReader xmlfer = xmlif.createFilteredReader(xmler, filter);

		
		//JAXBContext ctx = JAXBContext
		//		.newInstance("de.steri.xml.jaxb.domain");
		 JAXBContext ctx =
		 JAXBContext.newInstance(PersonType.class);
		Unmarshaller um = ctx.createUnmarshaller();
		
		
		while (xmler.hasNext()) {
			xmler.next();
			XMLEvent xmlElement = (XMLEvent) xmler.peek();
			if (xmlElement != null &&  xmlElement.isStartElement() == true) {
				StartElement e = (StartElement) xmlElement;
				//System.out.println(e.getName().getLocalPart()
				//		+ e.getEventType());
				if (e.getName().getLocalPart().equalsIgnoreCase("personen")) {

					// Parse into typed objects

					Object o = um.unmarshal(xmler);
					if (o instanceof PersonType) {
						PersonType bi = (PersonType) o;
						liste.add(bi);
					}
				}
			}
		}
		System.out.println(liste.size());
		fr.close();
	}
}
