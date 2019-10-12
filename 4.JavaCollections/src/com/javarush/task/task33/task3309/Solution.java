package com.javarush.task.task33.task3309;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, XMLStreamException, ParserConfigurationException, IOException, SAXException, TransformerException {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());


        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(obj, writer);


        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream stream = new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(stream);

        // Получение списка всех элементов  внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList tags = document.getDocumentElement().getElementsByTagName(tagName);

        // Перебор всех элементов
        for (int i = 0; i < tags.getLength(); i++) {
            Node tag = tags.item(i);

            tag.getParentNode().insertBefore( document.createComment( comment ), tag);

        }

        TransformerFactory tf = TransformerFactory.newInstance();

        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
        transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
        transformer.setOutputProperty( OutputKeys.STANDALONE, "no" );

        StringWriter sw = new StringWriter();

        transformer.transform(new DOMSource(document), new StreamResult(sw));

        return sw.toString();
    }

    public static void main(String[] args) throws JAXBException, XMLStreamException, IOException, SAXException, ParserConfigurationException, TransformerException {
        Cat cat = new Cat("Sima", 6);
        System.out.println(toXmlWithComment(cat, "temp", "comm"));
    }

    @XmlRootElement
    static class Cat {

        public String name;
        public int age;
        public String[] temp = {"dsfdfsdfsdf", "kkjkjkjk"};

        public Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Cat() {
        }
    }

}
