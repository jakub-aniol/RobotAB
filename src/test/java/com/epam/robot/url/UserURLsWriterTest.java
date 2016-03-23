package com.epam.robot.url;

import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UserURLsWriterTest {
    @Test
    public void testWritingInformationToFile() throws MalformedURLException {
        File file = new File(this.getClass().getResource("/testFile.test").getFile());
        URLList list = new URLList();
        URL expectedAddress = new URL("http://a");
        list.add("A",expectedAddress);
        UserURLsWriter.writeURLsToFile(list,file);
        URLList otherList = UserURLsReader.loadUserURLs(file);
        URL actualLoadedAddress = otherList.get("A");
        assertThat(actualLoadedAddress).isEqualTo(expectedAddress);
    }
}
