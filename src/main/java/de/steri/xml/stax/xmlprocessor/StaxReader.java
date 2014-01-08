package de.steri.xml.stax.xmlprocessor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import de.steri.xml.util.StaticVariables;

public class StaxReader {
	private String charEnc = "UTF-8";
	private String directoryPath = StaticVariables.DIRECTORY_PATH;
	private String xsdPath = StaticVariables.SCHEMA_PATH;
	
	public void readXMLFile() throws XMLStreamException, FileNotFoundException {
		long counter = 0;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory
				.createXMLStreamReader(new FileInputStream(StaticVariables.FILE));
		StringBuilder spacer = new StringBuilder();
		while (parser.hasNext()) {
			counter++;
			System.out.println("Event: " + parser.getEventType());
			switch (parser.getEventType()) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("START_DOCUMENT: " + parser.getVersion());
				break;
			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("END_DOCUMENT: ");
				parser.close();
				break;
			case XMLStreamConstants.NAMESPACE:
				System.out.println("NAMESPACE: " + parser.getNamespaceURI());
				break;
			case XMLStreamConstants.START_ELEMENT:
				spacer.append("  ");
				System.out.println(spacer + "START_ELEMENT: "
						+ parser.getLocalName());
				// Der Event XMLStreamConstants.ATTRIBUTE wird nicht geliefert!
				for (int i = 0; i < parser.getAttributeCount(); i++)
					System.out.println(spacer + "  Attribut: "
							+ parser.getAttributeLocalName(i) + " Wert: "
							+ parser.getAttributeValue(i));
				break;
			case XMLStreamConstants.CHARACTERS:
				if (!parser.isWhiteSpace())
					System.out.println(spacer + "  CHARACTERS: "
							+ parser.getText());
				break;
			case XMLStreamConstants.END_ELEMENT:
				System.out.println(spacer + "END_ELEMENT: "
						+ parser.getLocalName());
				spacer.delete(spacer.length()-2, spacer.length());
				break;
			default:
				break;
			}
			parser.next();
		}
		System.out.println(counter);
	}
	public void readXMLFileToObject() throws XMLStreamException, FileNotFoundException {
		long counter = 0;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory
				.createXMLStreamReader(new FileInputStream(StaticVariables.FILE));
		StringBuilder spacer = new StringBuilder();
		while (parser.hasNext()) {
			StaxToObjectMapper.dispatchEvent(parser);
		parser.next();
		}
	}
}
