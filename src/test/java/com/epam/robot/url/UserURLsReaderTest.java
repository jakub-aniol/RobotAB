package com.epam.robot.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UserURLsReaderTest {

    private File urlsFile;

    @BeforeMethod
    public void setUp() throws Exception {
        urlsFile = new File(this.getClass().getResource("/exampleUrls.properties").getFile());
    }

    @Test
    public void testLibraryNames() {
        URLList list = UserURLsReader.loadUserURLs(urlsFile);
        String libraryName = "JBC";
        URL entryFromList = list.get(libraryName);
        assertThat(entryFromList).isNotNull();
    }
}
