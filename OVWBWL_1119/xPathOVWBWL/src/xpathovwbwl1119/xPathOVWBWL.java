package xpathovwbwl1119;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class xPathOVWBWL {
    public static void main(String[] args) {
        try {
            File file = new File("studentOVWBWL.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            // String expression = "/class/student";
            // String expression = "/class/student[@id='2']";
            // String expression = "//student";
            // String expression = "/class/student[2]";
            // String expression = "/class/student[last()]";
            // String expression = "/class/student[last()-1]";
            // String expression = "/class/student[position()<3]";
            // String expression = "/class/*";
            String expression = "/class/student[//*[boolean(@*)]]";
            // String expression = "//*";

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                System.out.println("---------------");
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Id: " + eElement.getAttribute("id"));
                    Node node1 = eElement.getElementsByTagName("keresztnev").item(0);
                    System.out.println("Keresztnév: " + node1.getTextContent());
                    Node node2 = eElement.getElementsByTagName("vezeteknev").item(0);
                    System.out.println("Vezetéknév: " + node2.getTextContent());
                    Node node3 = eElement.getElementsByTagName("becenev").item(0);
                    System.out.println("Becenév: " + node3.getTextContent());
                    Node node4 = eElement.getElementsByTagName("kor").item(0);
                    System.out.println("Kor: " + node4.getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
