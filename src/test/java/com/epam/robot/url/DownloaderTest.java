package com.epam.robot.url;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class DownloaderTest {

    @Test
    public void testIfFileIsNotEmpty() throws MalformedURLException {
        Downloader downloader = new Downloader(new URL("http://jbc.bj.uj.edu.pl/dlibra/results.rss?type=latest&dirids=1&count=150&id=rss_2.0"));
        InputStream in = downloader.getStream();
        try {
            assertThat(in.read()).isNotNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testsGetStreamShouldThrowException_ForWrongURLAddress() throws MalformedURLException {
        //given
        boolean status = false;
        Downloader downloader = new Downloader(new URL("http://www.wp.pl"));

        //when
        InputStream is = downloader.getStream();

        //then
        assertThat(is).isNotEqualTo(null);
    }
}
