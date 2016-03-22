package com.epam.robot.url;

import com.epam.robot.records.Record;
import com.epam.robot.records.RecordParser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
                records.add(RecordParser.parse(items.item(x)));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
