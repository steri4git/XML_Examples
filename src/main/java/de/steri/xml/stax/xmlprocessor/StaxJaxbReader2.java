package de.steri.xml.stax.xmlprocessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import de.steri.xml.util.StaticVariables;

public class StaxJaxbReader2 {
	
	private static final String TEST_XSD_DATEI = "Buecher.xsd";
	private static final String TEST_XML_DATEI = "Buecher.xml";
	private static final String ENCODING       = "UTF-8";
    private static final String PACKAGE        = "de.meinefirma.buecherentities";
	private String charEnc = "UTF-8";
	private String directoryPath = StaticVariables.DIRECTORY_PATH;
	
	
	 /**
	    * Interface fuer Callback-Klassen fuer die Verarbeitung der einzelnen XML-Elemente.
	    */
	   public interface ElementeVerarbeitung
	   {
	      void verarbeite( Object element );
	   }

	   public void readXMLFile() throws IOException, Exception {
		Reader xml = new InputStreamReader(new FileInputStream(StaticVariables.FILE), charEnc);
		try {
		/**TODO MUSS GEFIXT werden
		 * return parseXmlElemente(xml, PACKAGE, elemVerarb);
		 */
		} finally {
			xml.close();
		}
	}

	public static long parseXmlElemente(Reader xml, String packageName,
			ElementeVerarbeitung elemVerarb) throws XMLStreamException,
			JAXBException {
		// StAX:
		EventFilter startElementFilter = new EventFilter() {
			public boolean accept(XMLEvent event) {
				return event.isStartElement();
			}
		};
		long anzahlElem = 0;
		XMLInputFactory staxFactory = XMLInputFactory.newInstance();
		XMLEventReader staxReader = staxFactory.createXMLEventReader(xml);
		XMLEventReader staxFiltRd = staxFactory.createFilteredReader(
				staxReader, startElementFilter);
		// Ueberspringe StartElement:
		staxFiltRd.nextEvent();
		// JAXB:
		JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		// Parsing:
		while (staxFiltRd.peek() != null) {
			Object element = unmarshaller.unmarshal(staxReader);
			elemVerarb.verarbeite(element);
			anzahlElem++;
		}
		return anzahlElem;
	}
	 static class ElementeSpeicherungInListe implements ElementeVerarbeitung
	   {
	      public List<Object> elemente = new ArrayList<Object>();

	      public void verarbeite( Object element )
	      {
	         this.elemente.add( element );
	      }
	   }
}
