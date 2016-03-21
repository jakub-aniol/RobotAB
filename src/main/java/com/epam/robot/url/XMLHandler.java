package com.epam.robot.url;

import com.epam.robot.Record;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler implements Parser {
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
            NodeList items = getNodeList(downloader.getStream(), "item");
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

    private void extractRecord(Node item) {
        NodeList children = item.getChildNodes();
        Node node;
        String url="";
        String title="";
        for (int x=0; x<children.getLength(); x++){
            node = children.item(x);
            if (node.getNodeName().equals("link"))
                url = node.getTextContent().replace("docmetadata?from=rss&", "rdf.xml?type=e&");
            else if (node.getNodeName().equals("title"))
                title = node.getTextContent();
        }
        records.add(new Record(title,url));
    }
}
