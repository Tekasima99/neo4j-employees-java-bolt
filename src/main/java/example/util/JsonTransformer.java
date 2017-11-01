package example.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * Converts objects to JSON format
 * @author Manoj Faria
 *
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}