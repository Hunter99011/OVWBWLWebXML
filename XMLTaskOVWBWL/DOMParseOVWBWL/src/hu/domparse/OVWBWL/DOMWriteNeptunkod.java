package hu.domparse.OVWBWL;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteNeptunkod {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Gyökér elem
            Element root = doc.createElement("KonyvtariRendszer");
            root.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaOVWBWL.xsd");
            doc.appendChild(root);

            Element konyvek = doc.createElement("Konyvek");
            root.appendChild(konyvek);
            addKonyv(doc, konyvek, "1", "A világ története", "Erkel Mária", "1989");
            addKonyv(doc, konyvek, "2", "Űrutazás alapjai", "Alma Aladár", "2001");
            addKonyv(doc, konyvek, "3", "Matematika alapjai", "Gyári Ferenc", "1977");

            Element peldanyok = doc.createElement("Peldanyok");
            root.appendChild(peldanyok);
            addPeldany(doc, peldanyok, "001", "1", "Jó", "101A", "igen");
            addPeldany(doc, peldanyok, "002", "2", "Közepes", "202B", "nem");
            addPeldany(doc, peldanyok, "003", "2", "Jó", "202C", "igen");
            addPeldany(doc, peldanyok, "004", "3", "Kiváló", "303C", "igen");

            Element kiadok = doc.createElement("Kiadok");
            root.appendChild(kiadok);
            addKiado(doc, kiadok, "1001", "Mentor Kiadó", "Budapest", new String[]{"+36 1 234 5678", "+36 1 356 3765"});
            addKiado(doc, kiadok, "1002", "Űrtudományi Kiadó", "Debrecen", new String[]{"+36 52 345 6789"});
            addKiado(doc, kiadok, "1003", "Amicus Kiadó", "Pécs", new String[]{"+36 72 456 7890", "+36 98 346 3169"});

            Element olvasok = doc.createElement("Olvasok");
            root.appendChild(olvasok);
            addOlvaso(doc, olvasok, "101", "Kovács", "Péter", "Budapest, Fő utca 1.", new String[]{"+36 30 123 4567", "+36 20 342 1927"});
            addOlvaso(doc, olvasok, "102", "Nagy", "Anna", "Debrecen, Kossuth utca 2.", new String[]{"+36 30 234 5678"});
            addOlvaso(doc, olvasok, "103", "Tóth", "János", "Pécs, Rákóczi út 3.", new String[]{"+36 30 345 6789"});

            Element tagdijak = doc.createElement("Tagdijak");
            root.appendChild(tagdijak);
            Element tagdij = doc.createElement("Tagdij");
            tagdij.setAttribute("ev", "2023");
            tagdij.appendChild(createElement(doc, "TagdijID", "100"));
            tagdij.appendChild(createElement(doc, "Osszeg", "1000"));
            tagdij.appendChild(createElement(doc, "Kedvezmeny", "200"));
            tagdijak.appendChild(tagdij);

            Element kiadasok = doc.createElement("Kiadasok");
            root.appendChild(kiadasok);
            addKiadas(doc, kiadasok, "1", "1001");
            addKiadas(doc, kiadasok, "2", "1002");
            addKiadas(doc, kiadasok, "3", "1003");

            Element kolcsonzesek = doc.createElement("Kolcsonzesek");
            root.appendChild(kolcsonzesek);
            addKolcsonzes(doc, kolcsonzesek, "001", "101", "001", "2024-01-01", "2024-01-10", "0");
            addKolcsonzes(doc, kolcsonzesek, "002", "102", "002", "2024-01-05", "2024-01-15", "500");
            addKolcsonzes(doc, kolcsonzesek, "003", "103", "003", "2024-01-10", "2024-01-20", "0");

            Element fizetesek = doc.createElement("Fizetesek");
            root.appendChild(fizetesek);
            addFizetes(doc, fizetesek, "100", "101", "2024-01-01", "Igen", "Készpénz");
            addFizetes(doc, fizetesek, "100", "102", "2024-08-05", "Nem", "Bankkártya");
            addFizetes(doc, fizetesek, "100", "103", "2024-01-10", "Igen", "Átutalás");

            // Fájlba kiírás
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLOVWBWL1.xml"));
            transformer.transform(source, result);

            System.out.println("A fa struktúra el lett mentve az XMLOVWBWL1.xml fájlba!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addKonyv(Document doc, Element parent, String id, String cim, String szerzo, String ev) {
        Element konyv = doc.createElement("Konyv");
        konyv.appendChild(createElement(doc, "KonyvID", id));
        konyv.appendChild(createElement(doc, "Cim", cim));
        konyv.appendChild(createElement(doc, "Szerzo", szerzo));
        konyv.appendChild(createElement(doc, "KiadasEve", ev));
        parent.appendChild(konyv);
    }

    private static void addPeldany(Document doc, Element parent, String leltar, String konyvID, String allapot, String polc, String kolcsonozheto) {
        Element peldany = doc.createElement("Peldany");
        peldany.appendChild(createElement(doc, "LeltariSzam", leltar));
        peldany.appendChild(createElement(doc, "KonyvID", konyvID));
        peldany.appendChild(createElement(doc, "Allapot", allapot));
        peldany.appendChild(createElement(doc, "Polcszam", polc));
        peldany.appendChild(createElement(doc, "Kolcsonozheto", kolcsonozheto));
        parent.appendChild(peldany);
    }

    private static void addKiado(Document doc, Element parent, String id, String nev, String szekhely, String[] telefonszamok) {
        Element kiado = doc.createElement("Kiado");
        kiado.appendChild(createElement(doc, "KiadoID", id));
        kiado.appendChild(createElement(doc, "Nev", nev));
        kiado.appendChild(createElement(doc, "Szekhely", szekhely));
        for (String tel : telefonszamok) {
            kiado.appendChild(createElement(doc, "Telefonszam", tel));
        }
        parent.appendChild(kiado);
    }

    private static void addOlvaso(Document doc, Element parent, String id, String vezeteknev, String keresztnev, String lakcim, String[] telefonszamok) {
        Element olvaso = doc.createElement("Olvaso");
        olvaso.appendChild(createElement(doc, "OlvasoID", id));
        Element nev = doc.createElement("Nev");
        nev.appendChild(createElement(doc, "Vezeteknev", vezeteknev));
        nev.appendChild(createElement(doc, "Keresztnev", keresztnev));
        olvaso.appendChild(nev);
        olvaso.appendChild(createElement(doc, "Lakcim", lakcim));
        for (String tel : telefonszamok) {
            olvaso.appendChild(createElement(doc, "Telefonszam", tel));
        }
        parent.appendChild(olvaso);
    }

    private static void addKiadas(Document doc, Element parent, String konyvID, String kiadoID) {
        Element kiadja = doc.createElement("Kiadja");
        kiadja.appendChild(createElement(doc, "KonyvID", konyvID));
        kiadja.appendChild(createElement(doc, "KiadoID", kiadoID));
        parent.appendChild(kiadja);
    }

    private static void addKolcsonzes(Document doc, Element parent, String kolcsonzesID, String olvasoID, String leltar, String datum, String visszahozva, String buntetes) {
        Element kolcsonzi = doc.createElement("Kolcsonzi");
        kolcsonzi.appendChild(createElement(doc, "KolcsonzesID", kolcsonzesID));
        kolcsonzi.appendChild(createElement(doc, "OlvasoID", olvasoID));
        kolcsonzi.appendChild(createElement(doc, "LeltariSzam", leltar));
        kolcsonzi.appendChild(createElement(doc, "Datum", datum));
        kolcsonzi.appendChild(createElement(doc, "Visszahozva", visszahozva));
        kolcsonzi.appendChild(createElement(doc, "Buntetes", buntetes));
        parent.appendChild(kolcsonzi);
    }

    private static void addFizetes(Document doc, Element parent, String tagdijID, String olvasoID, String datum, String kedvezmenyes, String fizetesiMod) {
        Element fizetes = doc.createElement("Fizetes");
        fizetes.appendChild(createElement(doc, "TagdijID", tagdijID));
        fizetes.appendChild(createElement(doc, "OlvasoID", olvasoID));
        fizetes.appendChild(createElement(doc, "Datum", datum));
        fizetes.appendChild(createElement(doc, "Kedvezmenyes", kedvezmenyes));
        fizetes.appendChild(createElement(doc, "FizetesiMod", fizetesiMod));
        parent.appendChild(fizetes);
    }

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
