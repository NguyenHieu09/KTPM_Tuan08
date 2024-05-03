package vn.edu.iuh.fit.module05;

import vn.edu.iuh.fit.core.DictionaryPlugin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VietnamseFrenchDictionaryPlugin implements DictionaryPlugin {
    private final String name = "VietnameseFrench";
    private final String filePath = "module05/vietphap.txt";

    @Override
    public String name() {
        return name;
    }

    @Override
    public Map<String, String> getDictionary() {
        Map<String, String> dictionary = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String word = parts[0].trim();
                    String meaning = parts[1].trim();
                    dictionary.put(word, meaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }
}
