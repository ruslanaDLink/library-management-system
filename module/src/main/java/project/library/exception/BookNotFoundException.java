package project.library.exception;

import project.library.model.Book;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message, Book book) {
        System.out.println("BookNotFoundException: " + message);
        System.out.println(book.getTitle() + " not found.");
    }
}
