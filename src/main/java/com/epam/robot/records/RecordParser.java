package com.epam.robot.records;

import com.epam.robot.url.Downloader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class has only one static method responsible for parsing a record from xml Node item.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class RecordParser {
    /**
     * This method create Record object from Node element from xml file.
     * @param item <code>Node</code> element that contains children with information about the record in the library.
     * @return <code>Record</code> object.
     */
    public static Record parse(Node item) {
        NodeList children = item.getChildNodes();
        Node node;
        String url = "";
        String title = "";
        String description = "";
        ArrayList<String>keyWord = new ArrayList<>();
        for (int x = 0; x < children.getLength(); x++) {
            node = children.item(x);
            if (node.getNodeName().equals("link")) {
                url = node.getTextContent().replace("docmetadata?from=rss&", "rdf.xml?type=e&");
                try {
                    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new Downloader(new URL(url)).getStream());

                    NodeList nodeList = document.getElementsByTagName("dc:subject");
                    for(int y = 0; y<nodeList.getLength(); y++){
                        keyWord.add(nodeList.item(y).getTextContent());
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

            }
            else if (node.getNodeName().equals("title"))
                title = node.getTextContent();
            else if (node.getNodeName().equals("description"))
                description = node.getTextContent();

        }
        return (new Record(title, url, description, keyWord));


    }


}
