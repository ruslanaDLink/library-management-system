package project.library.user;

import project.library.Library;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;

import java.util.List;
import java.util.logging.Logger;

public class UserImpl implements UserInterface {
    private static final Logger LOGGER = Logger.getLogger(UserImpl.class.getName());

    private final List<Book> myBooks;
    private final List<Book> libraryBooks;

    public UserImpl(List<Book> myBooks) {
        this.myBooks = myBooks;
        Library library = new Library();
        libraryBooks = library.books;
    }

    @Override
    public void add(Book book) throws BookNotFoundException {
        if (libraryBooks.contains(book) && book != null) {
            LOGGER.info("Added book to cart " + "#" + book.getId() + " \"" + book.getTitle() + "\"");
            myBooks.add(book);
        } else {
            LOGGER.warning("Failed to add book " + book);
            throw new BookNotFoundException("404 ERROR...");
        }
    }

    @Override
    public void remove(Book book) {
        myBooks.remove(book);
        LOGGER.info("Removed book #" + book.getId() + " from cart.");
    }

    @Override
    public void borrow(Book book) {
        try {
            if (libraryBooks.contains(book)) {
                myBooks.add(book);
                libraryBooks.remove(book);
                LOGGER.info("Borrowed book " + book);
            }
        } catch (Exception e) {
            LOGGER.info("Error occurred during borrowing book process. " + e.getMessage());
        }
    }

    @Override
    public Book searchByTitle(String title) {
        for (Book libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equalsIgnoreCase(title)) {
                LOGGER.info("Found book with specified title");
                return libraryBook;
            }
        }
        LOGGER.info("Not found anything.");
        return null;
    }

    @Override
    public Book searchByAuthor(String author) {
        for (Book libraryBook : libraryBooks) {
            if (libraryBook.getAuthor().equalsIgnoreCase(author)) {
                LOGGER.info("Found book with specified author");
                return libraryBook;
            }
        }
        LOGGER.info("Not found anything.");
        return null;
    }

    @Override
    public Book searchByGenre(String genre) {
        for (Book libraryBook : libraryBooks) {
            if (libraryBook.getGenre().name().equalsIgnoreCase(genre)) {
                LOGGER.info("Found book with specified genre");
                return libraryBook;
            }
        }
        LOGGER.info("Not found anything.");
        return null;
    }

    @Override
    public void returnBack(Book book) {
        if (myBooks.contains(book)) {
            myBooks.remove(book);
            libraryBooks.add(book);
            LOGGER.info("Returned book to library " + book);
        }
    }
}
