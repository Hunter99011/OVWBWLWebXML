package hu.domparse.OVWBWL;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DOMModifyOVWBWL{
    public static void main(String[] args) {
        try {
            // XML dokumentum betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("XMLOVWBWL.xml"));
            doc.getDocumentElement().normalize();

            // 1. Új könyv hozzáadása
            Element newBook = doc.createElement("Konyv");
            newBook.appendChild(createElement(doc, "KonyvID", "4"));
            newBook.appendChild(createElement(doc, "Cim", "Java programozás"));
            newBook.appendChild(createElement(doc, "Szerzo", "Kurucz Milán"));
            newBook.appendChild(createElement(doc, "KiadasEve", "2024"));
            doc.getElementsByTagName("Konyvek").item(0).appendChild(newBook);

            System.out.println("Új könyv hozzáadva:");
            printElement(newBook);
            System.out.println();

            // 2. Egy meglévő könyv címének frissítése
            NodeList books = doc.getElementsByTagName("Konyv");
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                if (book.getElementsByTagName("KonyvID").item(0).getTextContent().equals("2")) {
                    book.getElementsByTagName("Cim").item(0).setTextContent("Űrutazás mesterfokon");
                    System.out.println("Könyv címe frissítve:");
                    printElement(book);
                    System.out.println();
                }
            }

            // 3. Egy példány állapotának módosítása
            NodeList copies = doc.getElementsByTagName("Peldany");
            for (int i = 0; i < copies.getLength(); i++) {
                Element copy = (Element) copies.item(i);
                if (copy.getElementsByTagName("LeltariSzam").item(0).getTextContent().equals("001")) {
                    copy.getElementsByTagName("Allapot").item(0).setTextContent("Közepes");
                    System.out.println("Példány állapota frissítve:");
                    printElement(copy);
                    System.out.println();
                }
            }

            // 4. Egy olvasó lakcímének frissítése
            NodeList readers = doc.getElementsByTagName("Olvaso");
            for (int i = 0; i < readers.getLength(); i++) {
                Element reader = (Element) readers.item(i);
                if (reader.getElementsByTagName("OlvasoID").item(0).getTextContent().equals("103")) {
                    reader.getElementsByTagName("Lakcim").item(0).setTextContent("Szeged, Kossuth tér 5.");
                    System.out.println("Olvasó lakcíme frissítve:");
                    printElement(reader);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    private static void printElement(Element element) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            element.normalize();

            DOMSource source = new DOMSource(element);
            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));

            String result = writer.toString().replaceAll("(?m)^[ \t]*\\r?\\n", "").trim();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

