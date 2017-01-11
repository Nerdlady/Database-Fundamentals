package app.parsers.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class XmlParserImpl implements XmlParser {
    @Override
    public <T> T readFromXml(Class<T> classes, String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classes);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream file = getClass().getResourceAsStream(fileName);
        T objects = (T) unmarshaller.unmarshal(file);
        return objects;
    }

    @Override
    public <T> void writeToXml(T object, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(filePath);
        marshaller.marshal(object, file);
    }
}
