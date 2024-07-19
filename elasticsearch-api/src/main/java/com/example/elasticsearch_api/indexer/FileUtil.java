package com.example.elasticsearch_api.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;

public class FileUtil {

    public Map<String, Object> getFileContent(String filePath) {
        String str;
        StringBuilder stringBuilder = new StringBuilder(4000);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((new ClassPathResource(filePath)).getInputStream())
        )) {
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
            return new ObjectMapper().readValue(stringBuilder.toString(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
