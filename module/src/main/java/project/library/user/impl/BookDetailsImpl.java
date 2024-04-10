package project.library.user.impl;

import project.library.Library;
import project.library.model.Book;
import project.library.mybooks.BookDetails;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class BookDetailsImpl implements BookDetails {
    private static final Logger LOGGER = Logger.getLogger(BookDetailsImpl.class.getName());

    private UserImpl user;
    private List<Book> libraryBooks;

    public BookDetailsImpl() {
        user = new UserImpl();
        Library library = new Library();
        libraryBooks = library.getBooks();
    }

    public String getBookInfo(String name){


        for (Book libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equalsIgnoreCase(name)) {
                return getBookInfoByTitle(name);
            } else if (libraryBook.getAuthor().equalsIgnoreCase(name)) {
                return getBookInfoByAuthor(name);
            } else  {
                Long id = Long.parseLong(name);
                return getBookInfoById(id);
            }
        }
        return name;
    }

    @Override
    public String getBookInfoById(Long id) {
        for (Book libraryBook : libraryBooks) {
            if (Objects.equals(libraryBook.getId(), id)) {
                LOGGER.info("Book Information");
                return libraryBook.toString();
            } else {
                LOGGER.info("ID not found " + id);
            }
        }
        return null;
    }

    @Override
    public String getBookInfoByTitle(String title) {
        LOGGER.info("Book Information");
        return user.searchByTitle(title).toString();
    }

    @Override
    public String getBookInfoByAuthor(String author) {
        LOGGER.info("Book Information");
        return user.searchByAuthor(author).toString();
    }
}
