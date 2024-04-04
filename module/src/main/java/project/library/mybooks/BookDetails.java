package project.library.mybooks;

import project.library.exception.BookNotFoundException;

public interface BookDetails {

    String getBookInfoById(Long id);

    String getBookInfoByTitle(String title);

    String getBookInfoByAuthor(String author);

    String getBookInfo(String name) throws BookNotFoundException;
}
