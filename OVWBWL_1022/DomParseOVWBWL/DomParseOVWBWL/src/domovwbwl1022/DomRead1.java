package domovwbwl1022;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomRead1 {
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("OVWBWL_orarend.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("ora");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");
                String tipus = elem.getAttribute("tipus");

                Node node1 = elem.getElementsByTagName("targy").item(0);
                String oname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("idopont").item(0);
                String idopont = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("oktato").item(0);
                String oktato = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("szak").item(0);
                String szak = node5.getTextContent();

                System.out.printf("Óra id: %s%n", uid);
                System.out.printf("Óra típusa: %s%n", tipus);
                System.out.printf("Óra neve: %s%n", oname);
                System.out.printf("Óra időpontja: %s%n", idopont);
                System.out.printf("Helyszín: %s%n", helyszin);
                System.out.printf("Oktató: %s%n", oktato);
                System.out.printf("Szak: %s%n", szak);
            }
        }
    }
}
