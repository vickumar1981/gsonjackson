package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hello.model.Book;
import hello.model.BookList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    ObjectMapper mapper;

    public BookController(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

//        SimpleModule module = new SimpleModule();
//        module.addSerializer(new CustomBookWriter(Book.class));
//        mapper.registerModule(module);
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Book> postBook(@RequestBody Book book) throws JsonProcessingException {
        if (book != null) {
            System.out.println("\nname: " + book.toString() + "\n");
            System.out.println("\n" + mapper.writeValueAsString(book) + "\n");
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<BookList> getBooks() throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("book 1", Optional.of("author 1"), Optional.empty()));
        books.add(new Book("book 2", Optional.empty(), Optional.of(2018)));

        BookList bookList = new BookList(books);
        System.out.println("\nSERIALIZING USING JACKSON\n");
        String jsonResult = mapper.writeValueAsString(bookList);
        System.out.println("\n" + jsonResult + "\n");
//        System.out.println("\nDESERIALIZING USING JACKSON\n");
//        Book firstBook = mapper.readValue(jsonResult, BookList.class).getBooks().get(0);
//        System.out.print("\n" + "FIRST BOOK: " + firstBook.toString() + "\n");
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/gson", method = RequestMethod.POST)
    public ResponseEntity<Book> postBookGson(@RequestBody Book book) throws JsonProcessingException {
        Gson gson = new Gson();
        if (book != null) {
            System.out.println("\n" + book.toString() + "\n");
            System.out.println("\n" + gson.toJson(book) + "\n");
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/gson", method = RequestMethod.GET)
    public ResponseEntity<BookList> getBooksGson() throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("book 1", Optional.of("author 1"), Optional.empty()));
        books.add(new Book("book 2", Optional.empty(), Optional.of(2018)));

        BookList bookList = new BookList(books);
        System.out.println("\nSERIALIZING USING GSON\n");
        Gson gson = new Gson();

//        Gson gson= new GsonBuilder()
//                .setPrettyPrinting()
//                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
//                .disableHtmlEscaping()
//                .registerTypeAdapter(Book.class, new CustomBookWriterGson())
//                .create();

        String jsonResult = gson.toJson(bookList);
        System.out.println("\n" + jsonResult + "\n");
        System.out.println("\nDESERIALIZING USING GSON\n");
        Book firstBook = gson.fromJson(jsonResult, BookList.class).getBooks().get(0);
        System.out.print("\n" + "FIRST BOOK: " + firstBook.toString() + "\n");
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
