package hello.model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList (ArrayList<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
