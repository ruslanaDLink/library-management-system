package project.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import project.library.model.book.features.Genre;
import project.library.exception.IsbnFailValidation;

@Data
@Entity
@Table(name = "BOOK")
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
    @Column(name = "GENRE")
    private Genre genre;

    public Book(String title, String author, String isbn, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = validateBookIsbn(isbn);
        this.genre = genre;
    }

    private String validateBookIsbn(String isbn) {
        String str = isbn.replaceAll("-", "");
        return str.length() == 13 ? isbn : "ISBN must contain 13 digits. " + new IsbnFailValidation("Invalid ISBN.");
    }
}
