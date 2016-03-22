package com.epam.robot.url;

import com.epam.robot.records.Record;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DCMetadataParserTest {

    private DCMetadataParser parser;
    private Record record;

    @BeforeMethod
    public void setUp() throws Exception {
        parser = new DCMetadataParser();
        record = mock(Record.class);
    }

    @Test
    public void testCheckIfProvidedURLIsBook() throws FileNotFoundException {
        Record record = mock(Record.class);
        InputStream in = new FileInputStream(new File(XMLHandlerTest.class.getResource("/exampleE-Book.xml").getFile()));
        when(record.stream()).thenReturn(in);
        DCMetadataParser parser = new DCMetadataParser();
        assertThat(parser.isBook(record)).isTrue();
    }
    @Test
    public void testCheckIfProvidedURLIsNotBook() throws FileNotFoundException {
        Record record = mock(Record.class);
        InputStream in = new FileInputStream(new File(XMLHandlerTest.class.getResource("/exampleJournal.xml").getFile()));
        when(record.stream()).thenReturn(in);
        DCMetadataParser parser = new DCMetadataParser();
        assertThat(parser.isBook(record)).isFalse();
    }

    @Test
    public void testValidSameDate() {
        Date today = new Date(System.currentTimeMillis());
        when(record.getDate()).thenReturn(today);
        assertThat(parser.isDateInvalid(record)).isFalse();
    }

    @Test
    public void testValidYesterdayDate() {
        Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000+1000);
        when(record.getDate()).thenReturn(yesterday);
        assertThat(parser.isDateInvalid(record)).isFalse();
    }

    @Test
    public void testInvalidDate() {
        Date dayBeforeYesterday = new Date(System.currentTimeMillis()-24*60*60*1000-1000);
        when(record.getDate()).thenReturn(dayBeforeYesterday);
        assertThat(parser.isDateInvalid(record)).isTrue();
    }
}
