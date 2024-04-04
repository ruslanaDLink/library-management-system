package project.library;

import project.library.model.Book;
import project.library.model.book.features.Genre;
import project.library.services.servicesimpl.LibrarianBookHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> books;
    private LibrarianBookHelper librarianBookHelper;

    public Library() {
        books = new ArrayList<>();
        librarianBookHelper = new LibrarianBookHelper();
    }


    public void fillCollection() throws SQLException {
        Book agathaChristieTheSecretOfChimneys = new Book("The Secret Of Chimneys", "Agatha Christie", Genre.DETECTIVE, 1925);
        Book agathaChristieTheManBrownSuit = new Book("The Man in the Brown Suit", "Agatha Christie", Genre.DETECTIVE, 1924);
        Book agathaChristieDestinationUnknown = new Book("Destination Unknown", "Agatha Christie", Genre.NOVEL, 1954);
        Book agathaChristiePosternFate = new Book("Postern of Fate", "Agatha Christie", Genre.DETECTIVE, 1973);


        librarianBookHelper.add(agathaChristiePosternFate);
        librarianBookHelper.add(agathaChristieDestinationUnknown);
        librarianBookHelper.add(agathaChristieTheSecretOfChimneys);
        librarianBookHelper.add(agathaChristieTheManBrownSuit);
    }

    public static void main(String[] args) throws SQLException {
        Library library = new Library();
        library.fillCollection();
    }
}
