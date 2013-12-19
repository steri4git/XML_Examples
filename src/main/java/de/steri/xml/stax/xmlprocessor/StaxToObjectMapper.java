package de.steri.xml.stax.xmlprocessor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.*;

import de.steri.xml.stax.xmlprocessor.RootElemente;
import de.steri.xml.jaxb.domain.AdresseType;
import de.steri.xml.jaxb.domain.DateiType;
import de.steri.xml.jaxb.domain.PersonType;

public class StaxToObjectMapper implements XMLStreamConstants {

	private static DateiType datei;
	//private static final String MONTAG = "s";

	public static void dispatchEvent(XMLStreamReader stream)
			throws XMLStreamException {
		//System.out.println("dispatchEvent aufgerufen");
		
		switch (stream.getEventType()) {
		case XMLStreamConstants.START_DOCUMENT:
			break;
		case XMLStreamConstants.END_DOCUMENT:
			stream.close();
			break;
		case XMLStreamConstants.NAMESPACE:
			break;
		case XMLStreamConstants.START_ELEMENT:
			onStartElement(stream);
			break;
		case XMLStreamConstants.CHARACTERS:
			if (!stream.isWhiteSpace())
				break;
		case XMLStreamConstants.END_ELEMENT:
			break;
		default:
			break;
		}
	}

	private static void onStartElement(XMLStreamReader stream) throws XMLStreamException {
		System.out.println("onStartElement aufgerufen");
		createDateiType(stream)	;
		System.out.println(datei.getPersonen().size() + " Personen gefunden!");
	}

	private static void createDateiType(XMLStreamReader stream)
			throws XMLStreamException {
		//System.out.println("createDateiType aufgerufen");

		while (!(stream.getEventType() == XMLStreamConstants.END_ELEMENT & stream
				.getLocalName().equals(RootElemente.DATEITYPE))) {
			switch (stream.getLocalName()) {
			case RootElemente.DATEITYPE:
				datei = new DateiType();
				break;
			case RootElemente.DATUM:
				stream.next();
				datei.setDatum(stream.getText());
				System.out.println(datei.getDatum());
				break;
			case RootElemente.ERSTELLER:
				stream.next();
				datei.setErsteller(stream.getText());
				break;
			case RootElemente.PERSONEN:
				datei.getPersonen().add(createPersonType(stream));
				break;
			}
			stream.nextTag();
		}
	}

	private static PersonType createPersonType(XMLStreamReader stream)
			throws XMLStreamException {
		//System.out.println("createPersonType aufgerufen");
		PersonType person = new PersonType();
		while (!(stream.getEventType() == XMLStreamConstants.END_ELEMENT & stream
				.getLocalName().equals(RootElemente.PERSONEN))) {
			switch (stream.getLocalName()) {
			case PersonenElemente.NAME:
				stream.next();
				person.setName(stream.getText());
				break;
			case PersonenElemente.VORNAME:
				stream.next();
				person.setVorname(stream.getText());
				break;
			case PersonenElemente.ADRESSEN:
				person.getAdressen().add(createAdresseType(stream));
				break;
			}
			stream.nextTag();
		}
		return person;
	}

	private static AdresseType createAdresseType(XMLStreamReader stream)
			throws XMLStreamException {
		AdresseType adresse = new AdresseType();
		
		//System.out.println("createAdresseType aufgerufen");

		while (!(stream.getEventType() == XMLStreamConstants.END_ELEMENT
				& stream.getLocalName().equals(PersonenElemente.ADRESSEN))) {
			switch (stream.getLocalName()) {
			case AdressenElemente.HAUSNUMMER:
				stream.next();
				adresse.setHausnummer(stream.getText());
				break;
			case AdressenElemente.STRASSE:
				stream.next();
				adresse.setStrasse(stream.getText());
				break;
			case AdressenElemente.STADT:
				stream.next();
				adresse.setStadt(stream.getText());
				break;
			case AdressenElemente.PLZ:
				stream.next();
				adresse.setPlz(stream.getText());
				//System.out.println(adresse.getPlz());
				break;
			}
			stream.nextTag();
		}
		return adresse;
	}
}