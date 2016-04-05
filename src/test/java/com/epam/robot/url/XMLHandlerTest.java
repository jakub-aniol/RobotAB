package com.epam.robot.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XMLHandlerTest {

    private Downloader downloader;

    @BeforeMethod
    public void setUp() throws Exception {
        downloader = mock(Downloader.class);
        InputStream in = new FileInputStream(new File(XMLHandlerTest.class.getResource("/example.rss").getFile()));
        when(downloader.getStream()).thenReturn(in);
    }

    @Test
    public void testListOfRecordsInRSSFile() {
        XMLHandler xmlHandler = new XMLHandler(downloader);
        int expectedLinksListSize = 2;
        int actualLinksSize = xmlHandler.getRecords().size();
        assertThat(actualLinksSize).isEqualTo(expectedLinksListSize);
    }


}
