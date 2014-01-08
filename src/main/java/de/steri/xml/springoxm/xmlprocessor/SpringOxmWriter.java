package de.steri.xml.springoxm.xmlprocessor;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import de.steri.xml.util.StaticObjectFactory;
import de.steri.xml.util.StaticVariables;

public class SpringOxmWriter {
		
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
	
	public void writeXML(long anzahl) throws Exception{
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(StaticVariables.FILE);
			
			getMarshaller().marshal(StaticObjectFactory.createDateiType(anzahl), new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	

}
