package main;
import javax.swing.*;
import java.awt.*;

import java.util.Set;

public class DictionaryGUI extends JFrame {
    private JTextField searchField;
    private JTextArea meaningTextArea;
    private JComboBox<String> languageComboBox;

    private final DictionaryKernel kernel;

    public DictionaryGUI(DictionaryKernel kernel) {
        this.kernel = kernel;

        setTitle("Dictionary App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Search:");
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setHorizontalAlignment(JTextField.LEFT);
        searchField.setText("");

        searchPanel.add(searchField);

        JButton searchButton = new JButton();
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14));
        searchButton.setText("Search");
        searchPanel.add(searchButton);


        panel.add(searchPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel searchAndMeaningPanel = new JPanel();
        searchAndMeaningPanel.setLayout(new FlowLayout());

        JPanel searchPanelInside = new JPanel();
        searchPanelInside.setLayout(new FlowLayout());
        searchPanelInside.add(searchLabel);
        searchPanelInside.add(searchField);
        searchPanelInside.add(searchButton);

        searchAndMeaningPanel.add(searchPanelInside);

        centerPanel.add(searchAndMeaningPanel, BorderLayout.NORTH);

        meaningTextArea = new JTextArea(10, 20);
        JScrollPane meaningScrollPane = new JScrollPane(meaningTextArea);
        centerPanel.add(meaningScrollPane, BorderLayout.CENTER);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new FlowLayout());

        JLabel languageLabel = new JLabel("Language: ");
        languagePanel.add(languageLabel);

        languageComboBox = new JComboBox<>();
        Set<String> availableLanguages = kernel.getAvailableLanguages();
        for (String language : availableLanguages) {
            languageComboBox.addItem(language);
        }
        languagePanel.add(languageComboBox);

        panel.add(languagePanel, BorderLayout.NORTH);

        Font vietnameseFont = new Font("Arial Unicode MS", Font.PLAIN, 14);
        searchField.setFont(vietnameseFont);
        meaningTextArea.setFont(vietnameseFont);

        searchButton.addActionListener(e -> {
            String language = (String) languageComboBox.getSelectedItem();
            String word = searchField.getText();
            String result = kernel.search(language, word);
            meaningTextArea.setText(result);
        });
    }
}

