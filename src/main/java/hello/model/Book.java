package hello.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", year=" + year +
                '}';
    }

    private String name;
    private Optional<String> author;
    private Optional<Integer> year;

    public Book(String name, Optional<String> author, Optional<Integer> year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getAuthor() {
        return author;
    }

    public Optional<Integer> getYear() {
        return year;
    }
}
