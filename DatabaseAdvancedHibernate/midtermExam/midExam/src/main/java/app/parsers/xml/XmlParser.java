package app.parsers.xml;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <T> T readFromXml(Class<T> classes, String fileName) throws JAXBException;

    <T> void writeToXml(T object, String filePath) throws JAXBException;
}
