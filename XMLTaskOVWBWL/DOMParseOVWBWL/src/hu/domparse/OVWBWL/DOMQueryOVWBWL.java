package hu.domparse.OVWBWL;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DOMQueryOVWBWL {
    public static void main(String[] args) {
        try {
            // XML dokumentum beolvasása
            File xmlFile = new File("XMLOVWBWL.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 1. Lekérdezés: Könyvek címe és szerzője
            System.out.println("1. lekérdezés: Könyvek címe és szerzője:");
            NodeList konyvek = doc.getElementsByTagName("Konyv");
            for (int i = 0; i < konyvek.getLength(); i++) {
                Element konyv = (Element) konyvek.item(i);
                String cim = konyv.getElementsByTagName("Cim").item(0).getTextContent();
                String szerzo = konyv.getElementsByTagName("Szerzo").item(0).getTextContent();
                System.out.println("Cím: " + cim + ", Szerző: " + szerzo);
            }

            // 2. Lekérdezés: Kölcsönözhető példányok
            System.out.println("\n2. lekérdezés: Kölcsönözhető példányok:");
            NodeList peldanyok = doc.getElementsByTagName("Peldany");
            for (int i = 0; i < peldanyok.getLength(); i++) {
                Element peldany = (Element) peldanyok.item(i);
                String kolcsonozheto = peldany.getElementsByTagName("Kolcsonozheto").item(0).getTextContent();
                if ("igen".equalsIgnoreCase(kolcsonozheto)) {
                    String leltariSzam = peldany.getElementsByTagName("LeltariSzam").item(0).getTextContent();
                    System.out.println("Leltári szám: " + leltariSzam);
                }
            }

            // 3. Lekérdezés: Olvasók neve és lakcíme
            System.out.println("\n3. lekérdezés: Olvasók neve és lakcíme:");
            NodeList olvasok = doc.getElementsByTagName("Olvaso");
            for (int i = 0; i < olvasok.getLength(); i++) {
                Element olvaso = (Element) olvasok.item(i);
                String vezeteknev = olvaso.getElementsByTagName("Vezeteknev").item(0).getTextContent();
                String keresztnev = olvaso.getElementsByTagName("Keresztnev").item(0).getTextContent();
                String lakcim = olvaso.getElementsByTagName("Lakcim").item(0).getTextContent();
                System.out.println("Név: " + vezeteknev + " " + keresztnev + ", Lakcím: " + lakcim);
            }

            // 4. Lekérdezés: Kölcsönzések büntetés nélkül
            System.out.println("\n4. lekérdezés: Kölcsönzések büntetés nélkül:");
            NodeList kolcsonzesek = doc.getElementsByTagName("Kolcsonzi");
            for (int i = 0; i < kolcsonzesek.getLength(); i++) {
                Element kolcsonzes = (Element) kolcsonzesek.item(i);
                String buntetes = kolcsonzes.getElementsByTagName("Buntetes").item(0).getTextContent();
                if ("0".equals(buntetes)) {
                    String kolcsonzesID = kolcsonzes.getElementsByTagName("KolcsonzesID").item(0).getTextContent();
                    System.out.println("Kölcsönzés ID: " + kolcsonzesID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
