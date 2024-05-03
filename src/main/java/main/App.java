package main;//package main;


//import vn.edu.iuh.fit.core.DictionaryPlugin;
//
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//public class App {
//    public static void main(String[] args) {
//        try {
//            Properties properties = new Properties();
//            String path = new File("src/main/resources/application.properties").getAbsolutePath();
//            properties.load(new FileReader(path));
//
//            List<DictionaryPlugin> loadedPlugins = new ArrayList<>();
//            properties.forEach((key, value) -> {
//                try {
//                    PluginManager.loadPlugin(value.toString());
//                    DictionaryPlugin plugin = PluginManager.get(DictionaryPlugin.class);
//                    if (plugin != null) {
//                        loadedPlugins.add(plugin);
//                    } else {
//                        System.err.println("Failed to load dictionary plugin from file: " + value);
//                    }
//                } catch (Exception e) {
//                    System.err.println("Failed to load dictionary plugin from file: " + value);
//                    e.printStackTrace();
//                }
//            });
//
//            DictionaryKernel kernel = new DictionaryKernel();
//
//            for (DictionaryPlugin plugin : loadedPlugins) {
//                kernel.loadPlugin(plugin);
//            }
//
//            Set<String> availableLanguages = kernel.getAvailableLanguages();
//            System.out.println("Available languages: " + availableLanguages);
//
//            String language = "VietnameseFrench";
//            String word = "quả táo";
//            String result = kernel.search(language, word);
//
//            if (!result.equals("Word not found in the dictionary")) {
//                System.out.println("Meaning of '" + word + "' in " + language + ": " + result);
//            } else {
//                System.out.println("Word '" + word + "' not found in the " + language + " dictionary.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

import vn.edu.iuh.fit.core.DictionaryPlugin;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        try {

            System.setProperty("file.encoding", "UTF-8");

            // Load plugins và khởi tạo DictionaryKernel
            DictionaryKernel kernel = initializeDictionaryKernel();

            // Tạo GUI và chuyển DictionaryKernel cho nó
            SwingUtilities.invokeLater(() -> new DictionaryGUI(kernel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DictionaryKernel initializeDictionaryKernel() throws Exception {
        Properties properties = new Properties();
        String path = new File("src/main/resources/application.properties").getAbsolutePath();


        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));
        }

        List<DictionaryPlugin> loadedPlugins = new ArrayList<>();
        properties.forEach((key, value) -> {
            try {
                PluginManager.loadPlugin(value.toString());
                DictionaryPlugin plugin = PluginManager.get(DictionaryPlugin.class);
                if (plugin != null) {
                    loadedPlugins.add(plugin);
                } else {
                    System.err.println("Failed to load dictionary plugin from file: " + value);
                }
            } catch (Exception e) {
                System.err.println("Failed to load dictionary plugin from file: " + value);
                e.printStackTrace();
            }
        });

        DictionaryKernel kernel = new DictionaryKernel();
        for (DictionaryPlugin plugin : loadedPlugins) {
            kernel.loadPlugin(plugin);
        }
        return kernel;
    }
}

