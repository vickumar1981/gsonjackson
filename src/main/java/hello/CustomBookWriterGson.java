package hello;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import hello.model.Book;

import java.lang.reflect.Type;

public class CustomBookWriterGson implements JsonSerializer<Book> {
    @Override
    public JsonElement serialize(Book book, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject actorJsonObj = new JsonObject();
        actorJsonObj.addProperty("name", book.getName());
        actorJsonObj.addProperty("author", book.getAuthor().orElse("no author"));
        actorJsonObj.addProperty("year", book.getYear().orElse(0).intValue());
        return actorJsonObj;
    }
}
