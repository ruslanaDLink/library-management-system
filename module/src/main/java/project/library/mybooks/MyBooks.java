package project.library.mybooks;


import project.library.model.Book;

import java.util.List;

public interface MyBooks {

    List<Book> getCartBooks();

    List<Book> getReadBooks();

}
