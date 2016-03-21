package com.epam.robot;

import com.epam.robot.url.Downloader;
import com.epam.robot.url.URLList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DownloaderTest {

    private URLList urlList;

    @BeforeMethod
    public void setUp() throws Exception {
        urlList = mock(URLList.class);
        when(urlList.get(0)).thenReturn(new URL("http://jbc.bj.uj.edu.pl/dlibra/results.rss?type=latest&dirids=1&count=150&id=rss_2.0"));
    }

    @Test
    public void testIfFileIsNotEmpty() {
        Downloader downloader = new Downloader(urlList.get(0));
        InputStream in = downloader.getStream();
        try {
            assertThat(in.read()).isNotNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
