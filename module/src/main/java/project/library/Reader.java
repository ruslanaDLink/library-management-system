package project.library;

import project.library.exception.BookNotFoundException;
import project.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    private List<Book> bookList;

    public Reader() {
        bookList = new ArrayList<>();
    }


    public void returnBook(Book book) {
        bookList.remove(book);
    }

    public void borrowBook(Book book) throws BookNotFoundException {
        if (checkIfBookPresent(book)) {
            bookList.add(book);
        } else {
            String notFoundException = "The book was not found in the library system.";
            throw new BookNotFoundException(notFoundException, book);
        }
    }



    public boolean checkIfBookPresent(Book book) {
        return book != null;
    }


}
