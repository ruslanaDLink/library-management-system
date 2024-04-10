package project.library.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BookDetailsImplTest {
    private BookDetailsImpl bookDetails;

    @BeforeEach
    void setUp() {
        bookDetails = new BookDetailsImpl();
    }

    @Test
    void testGetBookInfo(){
        //given
        String author = "Agatha Christie";

        //when
        String bookInfo = bookDetails.getBookInfo(author);

        //then
        Assertions.assertNotNull(bookInfo, "Book not found.");
    }

    @Test
    void testGetBookInfoById() {
        //given
        Long id = 1L;

        //when
        String bookInfoById = bookDetails.getBookInfoById(id);

        //then
        Assertions.assertNotNull(bookInfoById, "Book not found by id " + id);
    }

    @Test
    void testGetBookInfoByTitle() {
        //given
        String title = "Harry Potter and the Goblet of Fire";

        //when
        String bookInfoByTitle = bookDetails.getBookInfoByTitle(title);

        //then
        Assertions.assertNotNull(bookInfoByTitle, "Book not found by title " + title);
    }

    @Test
    void testGetBookInfoByAuthor() {
        //given
        String author = "J.K.Rowling";

        //when
        String bookInfoByAuthor = bookDetails.getBookInfoByAuthor(author);

        //then
        Assertions.assertNotNull(bookInfoByAuthor, "Book not found by author " + author);
    }
}