package project.library.model;

import lombok.Data;
import project.library.model.book.features.Genre;
import project.library.model.book.features.Language;
import project.library.exception.IsbnFailValidation;

@Data
public class Book {
    private String title;
    private String author;
    private String isbn;
    private Language language;
    private Genre genre;

    public Book(String title, String author, String isbn, Language language, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = validateBookIsbn(isbn);
        this.language = language;
        this.genre = genre;
    }

    private String validateBookIsbn(String isbn) {
        String str = isbn.replaceAll("-", "");
        return str.length() == 13 ? isbn : "ISBN must contain 13 digits. " + new IsbnFailValidation("Invalid ISBN.");
    }
}
