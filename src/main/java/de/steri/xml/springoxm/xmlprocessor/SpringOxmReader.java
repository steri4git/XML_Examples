package de.steri.xml.springoxm.xmlprocessor;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import de.steri.xml.jaxb.domain.DateiType;
import de.steri.xml.util.StaticObjectFactory;
import de.steri.xml.util.StaticVariables;

public class SpringOxmReader {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
 
	public Marshaller getMarshaller() {
		return marshaller;
	}
 
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
 
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}
 
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	public void readXML() throws Exception{
		FileInputStream is = null;
        try {
            is = new FileInputStream(StaticVariables.FILE);
            DateiType datei = (DateiType)getUnmarshaller().unmarshal(new StreamSource(is));
            System.out.println(datei.getPersonen().size());
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}