package project.library.user;

import project.library.Library;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;
import project.library.mybooks.MyBooks;
import project.library.services.servicesimpl.LibrarianBookHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserImpl implements UserInterface, MyBooks {
    private static final Logger LOGGER = Logger.getLogger(UserImpl.class.getName());

    private List<Book> libraryBooks;
    private List<Book> myBooks;
    private List<Book> readBooks;
    private Library library;
    private LibrarianBookHelper librarianBookHelper;

    public UserImpl() {
        myBooks = new ArrayList<>();
        readBooks = new ArrayList<>();
        library = new Library();
        library = new Library();
        libraryBooks = library.books;
    }

    @Override
    public void add(Book book) throws BookNotFoundException {
        if (librarianBookHelper.validateBook(book) && book != null) {
            LOGGER.info("Added book to cart " + "#" + book.getId() + " \"" + book.getTitle() + "\"");
            myBooks.add(book);
        } else {
            LOGGER.warning("Failed to add book " + book);
            throw new BookNotFoundException("Invalid argument.");
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
        LOGGER.info("Title not found " + title);
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
        LOGGER.info("Author not found " + author);
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
        LOGGER.info("Genre not found " + genre);
        return null;
    }


    @Override
    public void returnBack(Book book) {
        if (myBooks.contains(book)) {
            myBooks.remove(book);
            readBooks.add(book);
            libraryBooks.add(book);
            LOGGER.info(book + " book returned to library.");
        }
    }

    @Override
    public List<Book> getCartBooks() {
        return myBooks;
    }

    @Override
    public List<Book> getReadBooks() {
        return readBooks;
    }
}
