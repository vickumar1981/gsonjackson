package hello;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hello.model.Book;

import java.io.IOException;

public class CustomBookWriter extends StdSerializer<Book> {
    public CustomBookWriter(Class t) {
        super(t);
    }

    @Override
    public void serialize(Book book, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", book.getName());
        jsonGenerator.writeStringField("author", book.getAuthor().orElse("no author"));
        jsonGenerator.writeNumberField("year", book.getYear().orElse(0).intValue());
        jsonGenerator.writeEndObject();
    }

}
