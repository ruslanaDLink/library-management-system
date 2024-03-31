package project.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import project.library.model.book.features.Genre;
import project.library.model.book.features.Language;
import project.library.exception.IsbnFailValidation;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "AUTHOR", nullable = false)
    private String author;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "LANGUAGE")
    private Language language;
    @Column(name = "GENRE")
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
