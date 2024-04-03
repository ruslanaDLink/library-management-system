package project.library.model;

import lombok.Data;
import project.library.model.book.features.Genre;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Genre genre;
    private int publishedYear;

    public Book(String title, String author, Genre genre, int publishedYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
    }

}
