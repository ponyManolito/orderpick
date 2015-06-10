package security.orderpick.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XmlUtil {
	/**
	 * Covierte POJO to xml document
	 * @param source POJO to convert
	 * @return
	 */
    public String convertToXml(Object source) {
        String result;
        StringWriter sw = new StringWriter();
        try {
            JAXBContext carContext = JAXBContext.newInstance(source.getClass());
            Marshaller carMarshaller = carContext.createMarshaller();
            carMarshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}