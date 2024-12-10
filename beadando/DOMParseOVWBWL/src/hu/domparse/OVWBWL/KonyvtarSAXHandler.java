package hu.domparse.OVWBWL;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class KonyvtarSAXHandler extends DefaultHandler {
    private String currentElement;
    private StringBuilder currentText = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        currentText.setLength(0); // Reset buffer
        if ("Konyv".equals(qName)) {
            System.out.println("\nKönyv:");
        } else if ("Peldany".equals(qName)) {
            System.out.println("\nPéldány:");
        } else if ("Olvaso".equals(qName)) {
            System.out.println("\nOlvasó:");
        } else if ("Tagdij".equals(qName)) {
            System.out.println("\nTagdíj:");
        } else if ("Kolcsonzi".equals(qName)) {
            System.out.println("\nKölcsönzés:");
        } else if ("Fizetes".equals(qName)) {
            System.out.println("\nFizetés:");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String text = currentText.toString().trim();
        switch (qName) {
            case "KonyvID":
                System.out.println("Könyv ID: " + text);
                break;
            case "Cim":
                System.out.println("Cím: " + text);
                break;
            case "Szerzo":
                System.out.println("Szerző: " + text);
                break;
            case "KiadasEve":
                System.out.println("Kiadás éve: " + text);
                break;
            case "LeltariSzam":
                System.out.println("Leltári szám: " + text);
                break;
            case "Allapot":
                System.out.println("Állapot: " + text);
                break;
            case "Polcszam":
                System.out.println("Polcszám: " + text);
                break;
            case "Kolcsonozheto":
                System.out.println("Kölcsönözhető: " + text);
                break;
            case "Nev":
                System.out.println("Név: " + text);
                break;
            case "Lakcim":
                System.out.println("Lakcím: " + text);
                break;
            case "Telefonszam":
                System.out.println("Telefonszám: " + text);
                break;
            case "Osszeg":
                System.out.println("Összeg: " + text);
                break;
            case "Kedvezmeny":
                System.out.println("Kedvezmény: " + text);
                break;
            case "Datum":
                System.out.println("Dátum: " + text);
                break;
            case "Visszahozva":
                System.out.println("Visszahozva: " + text);
                break;
            case "Buntetes":
                System.out.println("Büntetés: " + text);
                break;
            case "FizetesiMod":
                System.out.println("Fizetési mód: " + text);
                break;
            case "KiadoID":
                System.out.println("Kiadó ID: " + text);
                break;
            case "Szekhely":
                System.out.println("Kiadó székhelye: " + text);
                break;
        }
        currentElement = null;
    }
}
