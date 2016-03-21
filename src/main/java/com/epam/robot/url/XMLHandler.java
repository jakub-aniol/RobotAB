package com.epam.robot.url;

import com.epam.robot.Record;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {
    Downloader downloader;
    private List<Record> records;

    public XMLHandler(Downloader downloader) {
        this.downloader = downloader;
    }

    public List<Record> getRecords() {
        if (records == null) load();
        return records;
    }

    private void load() {
        records = new ArrayList<>();
        try {
            NodeList items = getNodeList();
            for (int x=0; x<items.getLength(); x++){
                extractRecord(items.item(x));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private NodeList getNodeList() throws SAXException, IOException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(downloader.getStream());
        return doc.getElementsByTagName("item");
    }

    private void extractRecord(Node item) {
        NodeList children = item.getChildNodes();
        Node node;
        String url="";
        String title="";
        for (int x=0; x<children.getLength(); x++){
            node = children.item(x);
            String t = node.getNodeName();
            if (node.getNodeName().equals("#text")) continue;
            if (node.getNodeName().equals("link"))
                url = node.getTextContent();
            else if (node.getNodeName().equals("title"))
                title = node.getTextContent();
        }
        records.add(new Record(title,url));
    }
}
