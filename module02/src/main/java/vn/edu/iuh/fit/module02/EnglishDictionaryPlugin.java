//package vn.edu.ueh.bit.module02;
//
//import vn.edu.ueh.bit.core.DictionaryPlugin;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EnglishDictionaryPlugin implements DictionaryPlugin {
//    private final String name = "English";
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public Map<String, String> getDictionary() {
//
//        Map<String, String> dictionary = new HashMap<>();
//
//        dictionary.put("hello", "xin chào");
//        dictionary.put("goodbye", "tạm biệt");
//        return dictionary;
//    }
//}

package vn.edu.iuh.fit.module02;

import vn.edu.iuh.fit.core.DictionaryPlugin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnglishDictionaryPlugin implements DictionaryPlugin {
    private final String name = "EnglishVietnamese";
    private final String filePath = "module02/anhviet.txt";

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
