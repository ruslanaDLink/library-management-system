package project.library.service;

import org.springframework.stereotype.Service;
import project.library.model.Book;

@Service
public class BookService {

    public Book addBook(Book book) {
        return book;
    }
}
