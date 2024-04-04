package project.library;

import project.library.model.Book;
import project.library.model.book.features.Genre;
import project.library.services.servicesimpl.LibrarianBookHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }


    public void fillCollection() throws SQLException {
        LibrarianBookHelper librarianBookHelper = new LibrarianBookHelper();

        Book joanRowlingHarryPotter1Part = new Book(
                "Harry Potter and the Philosopher’s Stone", "J.K.Rowling", Genre.FANTASY, 1997);
        Book joanRowlingHarryPotter2Part = new Book(
                "Harry Potter and the Chamber of Secrets", "J.K.Rowling", Genre.FANTASY, 1998);
        Book joanRowlingHarryPotter3Part = new Book(
                "Harry Potter and the Prisoner of Azkaban", "J.K.Rowling", Genre.FANTASY, 1999);
        Book joanRowlingHarryPotter4Part = new Book(
                "Harry Potter and the Goblet of Fire", "J.K.Rowling", Genre.FANTASY, 2000);
        Book joanRowlingHarryPotter5Part = new Book(
                "Harry Potter and the Order of the Phoenix", "J.K.Rowling", Genre.FANTASY, 2003);
        Book joanRowlingHarryPotter6Part = new Book(
                "Harry Potter and the Half-Blood Prince", "J.K.Rowling", Genre.FANTASY, 2005);
        Book joanRowlingHarryPotter7Part = new Book(
                "Harry Potter and the Deathly Hallows", "J.K.Rowling", Genre.FANTASY, 2007);


        librarianBookHelper.add(joanRowlingHarryPotter1Part);
        librarianBookHelper.add(joanRowlingHarryPotter2Part);
        librarianBookHelper.add(joanRowlingHarryPotter3Part);
        librarianBookHelper.add(joanRowlingHarryPotter4Part);
        librarianBookHelper.add(joanRowlingHarryPotter5Part);
        librarianBookHelper.add(joanRowlingHarryPotter6Part);
        librarianBookHelper.add(joanRowlingHarryPotter7Part);
    }

    public static void main(String[] args) throws SQLException {
        Library library = new Library();
        library.fillCollection();
    }
}
