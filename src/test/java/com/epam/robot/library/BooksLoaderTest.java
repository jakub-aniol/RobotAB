package com.epam.robot.library;

import com.epam.robot.records.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class BooksLoaderTest {

    private BooksLoader loader;

    @BeforeTest
    public void setUp() throws Exception {
        loader = new BooksLoader(new File(BooksLoader.class.getResource("/books.log").getFile()));
    }

    @Test
    public void testLoadedBooksNumber() {
        int expectedSize = 3;
        int actualSize = loader.numberOfBooks();
        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    public void testWhetherABookComesFromRightLibrary() {
        String expectedLibrary = "JBC";
        String actualLibrary = loader.get(0).getLibrary();
        assertThat(actualLibrary).isEqualTo(expectedLibrary);
    }
}
