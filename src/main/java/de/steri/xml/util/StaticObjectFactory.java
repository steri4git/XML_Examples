package de.steri.xml.util;

import de.steri.xml.jaxb.domain.AdresseType;
import de.steri.xml.jaxb.domain.DateiType;
import de.steri.xml.jaxb.domain.PersonType;

public class StaticObjectFactory {
	
	public static DateiType createDateiType (long anzahl){
		DateiType datei = new DateiType();
		datei.setDatum("05.09.2013");
		datei.setErsteller("Stefan");
		
		for (int i = 0; i < anzahl; i++) {
			datei.getPersonen().add(createPersonType());
		}
		return datei;
	}
		
	
	public static  PersonType createPersonType(){
		PersonType person = new PersonType();
		person.setName("Rischmueller" + "\u25C4" );
		person.setVorname("Stefan");
		person.getAdressen().add(createAdresseType());
		return person;
	}
	
	public static  AdresseType createAdresseType() {
		AdresseType adresse = new AdresseType();
		adresse.setHausnummer("9");
		adresse.setPlz("11833");
		adresse.setStadt("Hannover");
		adresse.setStrasse("Musterstraße");
		return adresse;
	}
}