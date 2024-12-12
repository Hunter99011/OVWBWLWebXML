package xpathneptunkod1126;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;

public class xPathModify {
    public static void main(String[] args) {
        try {
            // Beolvasás
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("orarendOVWBWL.xml"));

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            // 1. Módosítás: szak nevének módosítása
            Node szakNode = (Node) xpath.evaluate("//ora/szak", doc, XPathConstants.NODE);
            if (szakNode != null) {
                szakNode.setTextContent("Informatikai Mérnöki MSc.");
            }

            // 2. Módosítás: tárgy nevéhez monogram hozzáfűzése
            NodeList targyak = (NodeList) xpath.evaluate("//ora/targy", doc, XPathConstants.NODESET);
            for (int i = 0; i < targyak.getLength(); i++) {
                Node targyNode = targyak.item(i);
                String targyNev = targyNode.getTextContent();
                targyNode.setTextContent(targyNev + " (KM)");
            }

            // 3. Módosítás: id=3 helyszín módosítása
            Node helyszinNode = (Node) xpath.evaluate("//ora[@id='3']/helyszin", doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("XXXVII");
            }

            // Eredmény kiírása konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            // Mentés fájlba
            StreamResult fileResult = new StreamResult(new File("orarendNeptunkod1.xml"));
            transformer.transform(source, fileResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
