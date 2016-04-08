package com.epam.robot.records;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordParserTest {
    @Test
    public void testRecordFromXMLItem() throws ParserConfigurationException, IOException, SAXException {
        try (InputStream stream = RecordParserTest.class.getResource("/example.rss").openStream()) {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            Record record = RecordParser.parse(doc.getElementsByTagName("item").item(0));
            assertThat(record).isNotNull();
        }
    }

}
