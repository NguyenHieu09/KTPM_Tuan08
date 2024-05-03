package main;//package main;//package main;


import vn.edu.iuh.fit.core.DictionaryPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryKernel {
    private final Map<String, DictionaryPlugin> plugins;

    public DictionaryKernel() {
        plugins = new HashMap<>();
    }

    // Thêm phương thức để tải plugin vào kernel
    public void loadPlugin(DictionaryPlugin plugin) {
        plugins.put(plugin.name(), plugin);
    }

    // Lấy danh sách các ngôn ngữ có sẵn từ kernel
    public Set<String> getAvailableLanguages() {
        return plugins.keySet();
    }

    // Tìm kiếm từ trong từ điển
    public String search(String language, String word) {
        if (!plugins.containsKey(language)) {
            System.out.println("Plugin for " + language + " not found!");
            return "Language plugin not found";
        }

        DictionaryPlugin plugin = plugins.get(language);
        Map<String, String> dictionary = plugin.getDictionary();
        if (dictionary == null || !dictionary.containsKey(word)) {
            System.out.println("Word '" + word + "' not found in the " + language + " dictionary.");
            return "Word not found in the dictionary";
        }

        return dictionary.get(word);
    }
}


