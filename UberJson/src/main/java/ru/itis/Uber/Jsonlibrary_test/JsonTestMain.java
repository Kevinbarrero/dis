package ru.itis.Uber.Jsonlibrary_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonTestMain {
    public static void main(String[] args) {
        String jsonsource = "{ \"title\": \"testJson\"}";

        try {
            JsonNode node = Json.parse(jsonsource);
            System.out.println(node.get("title").asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
