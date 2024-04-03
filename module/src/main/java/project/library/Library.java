package project.library;

import project.library.model.Book;
import project.library.model.book.features.Genre;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> books;
    private Librarian librarian;

    public Library() {

    }

    public void init() {
        books = new ArrayList<>();
        librarian = new Librarian();
    }

    public void fillCollection() throws SQLException {
        Book agathaChristieTheSecretOfChimneys = new Book("The Secret Of Chimneys", "Agatha Christie", Genre.DETECTIVE, 1925);
        Book agathaChristieTheManBrownSuit = new Book("The Man in the Brown Suit", "Agatha Christie", Genre.DETECTIVE, 1924);
        Book agathaChristieDestinationUnknown = new Book("Destination Unknown", "Agatha Christie", Genre.NOVEL, 1954);
        Book agathaChristiePosternFate = new Book("Postern of Fate", "Agatha Christie", Genre.DETECTIVE, 1973);


        librarian.addNewBookToCollection(agathaChristiePosternFate);
        librarian.addNewBookToCollection(agathaChristieDestinationUnknown);
        librarian.addNewBookToCollection(agathaChristieTheSecretOfChimneys);
        librarian.addNewBookToCollection(agathaChristieTheManBrownSuit);
    }

    public static void main(String[] args) throws SQLException {
        Library library = new Library();
        library.init();
        library.fillCollection();
    }
}
