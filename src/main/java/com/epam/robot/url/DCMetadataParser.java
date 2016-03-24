package com.epam.robot.url;

import com.epam.robot.records.Record;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

/**
 * This class can determine whether a xml entry is a book and was uploaded today or yesterday.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class DCMetadataParser implements Parser {
    /**
     * This method check if the type of the record is a book.
     * @param record - object with address where record description can be found.
     * @return <code>true</code> when type of the record is a book.
     */
    public boolean isBook(Record record) {
        try {
            NodeList types = getNodeList(record.stream(), "dc:type");
            for (int i = 0; i < types.getLength(); i++) {
                if (types.item(i).getTextContent().contains("książka")) return true;
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method check if the date of the record is recent.
     * @param record - object with address where record description can be found.
     * @return <code>true</code> when date of uploading the record is recent.
     */
    public boolean isDateInvalid(Record record) {
        Date today = new Date(System.currentTimeMillis());
        Date recordDate = record.getDate();
        return Math.abs(today.getTime() - recordDate.getTime()) > 24 * 60 * 60 * 1000;
    }
}
