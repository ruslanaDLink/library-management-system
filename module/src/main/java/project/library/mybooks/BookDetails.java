package project.library.mybooks;

public interface BookDetails {

    String getBookInfoById(Long id);

    String getBookInfoByTitle(String title);

    String getBookInfoByAuthor(String author);
}
