package com.epam.robot.url;

import com.epam.robot.Record;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DCMetadataParserTest {


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
}
