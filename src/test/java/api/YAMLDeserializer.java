package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YAMLDeserializer {

    public static Map<String, String> fromFileToMap(String fileName) {

        Map<String, String> map = new HashMap<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            map = om.readValue(file, Map.class);

            return map;

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }

}