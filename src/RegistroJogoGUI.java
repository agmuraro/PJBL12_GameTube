package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroJogoGUI extends  JFrame {
    private JTextField nameField;
    private JTextField descricaoField;
    private JTextField priceField;
    private JTextField imageField;

    public RegistroJogoGUI() {
        setTitle("Registrar Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Name:");
        rightPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        JLabel descricaoLabel = new JLabel("Descrição:");
        rightPanel.add(descricaoLabel, gbc);

        gbc.gridy = 2;
        JLabel priceLabel = new JLabel("Price:");
        rightPanel.add(priceLabel, gbc);

        gbc.gridy = 3;
        JLabel imageLabel = new JLabel("Image:");
        rightPanel.add(imageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(20);
        rightPanel.add(nameField, gbc);

        gbc.gridy = 1;
        descricaoField = new JTextField(20);
        rightPanel.add(descricaoField, gbc);

        gbc.gridy = 2;
        priceField = new JTextField(20);
        rightPanel.add(priceField, gbc);

        gbc.gridy = 3;
        imageField = new JTextField(20);
        rightPanel.add(imageField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registroButton = new JButton("Registrar");
        rightPanel.add(registroButton, gbc);

        gbc.gridy = 5;



        registroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Sla oq faz aki
            }
        });

        panel.add(rightPanel, BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroGUI());
    }
}

