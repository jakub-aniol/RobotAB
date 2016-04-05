package com.epam.robot.library;

import com.epam.robot.records.Book;
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

    @Test
    public void testIfBookIsBotLogged() {
        //given
        Book book = new Book("JBC|Nowy");
        boolean result;
        boolean notLogged = true;
        //when
        result = loader.isNotLogged(book);
        //then
        assertThat(notLogged).isEqualTo(result);
    }

   /* @Test
    public void testIfBookLoaderIsCreatedWhenProperConstructor(){

        File file = new File("books.log");
        BooksLoader booksLoader = new BooksLoader(file);
        //when
        boolean expected = true;

        assertThat(file).isEqualTo(true);

    }*/


   /* public BooksLoader(File file) {
        new CheckBookStatusSubscriber(this);
        new BooksQuerySubscriber(this);
        books = new ArrayList<>();
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) !=null){
                books.add(new Book(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


}