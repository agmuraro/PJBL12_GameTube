package src;

import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class profileEditGUI extends JFrame {

        private JSONObject session;
        private JTextField nameField;
        private JTextField emailField;
        private JTextField senhaField;
        private JTextField usernameField;
        private JTextField profilePicField;
        private JLabel profilePicLabel;
        private JButton selectFileButton;

        public profileEditGUI(JSONObject session) {
            if (!session.has("name")) {
                // Handle session not available
                // ...
            }

            this.session = session;

            try {
                if (session.has("name")) {
                    // Create and configure components
                    nameField = new JTextField(20);
                    emailField = new JTextField(20);
                    senhaField = new JTextField(20);
                    usernameField = new JTextField(20);
                    profilePicLabel = new JLabel("Foto de Perfil:");
                    selectFileButton = new JButton("Select File");
                    selectFileButton.setBackground(Color.DARK_GRAY);
                    selectFileButton.setForeground(Color.WHITE);
                    selectFileButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            int result = fileChooser.showOpenDialog(null);
                            if (result == JFileChooser.APPROVE_OPTION) {
                                File selectedFile = fileChooser.getSelectedFile();
                                // Perform logic to handle the selected file
                            }
                        }
                    });

                    JLabel nameLabel = new JLabel("Nome:");
                    JLabel emailLabel = new JLabel("Email:");
                    JLabel senhaLabel = new JLabel("Senha:");
                    JLabel usernameLabel = new JLabel("Username:");

                    JButton saveButton = new JButton("Save");
                    saveButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            saveProfile();
                        }
                    });
                    saveButton.setBackground(Color.DARK_GRAY);
                    saveButton.setForeground(Color.WHITE);

                    JButton goBackButton = new JButton("Go Back");
                    goBackButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new Perfil(session).setVisible(true);
                            dispose();
                            // Add code to navigate back to the previous window
                        }
                    });
                    goBackButton.setBackground(Color.DARK_GRAY);
                    goBackButton.setForeground(Color.WHITE);

                    // Configure layout
                    setLayout(new GridBagLayout());
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setSize(400, 350);
                    getContentPane().setBackground(Color.DARK_GRAY);

                    // Create GridBagConstraints to manage the component placement
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the components

                    // Add components to the frame
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    add(nameLabel, gbc);

                    gbc.gridx = 1;
                    add(nameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    add(emailLabel, gbc);

                    gbc.gridx = 1;
                    add(emailField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    add(senhaLabel, gbc);

                    gbc.gridx = 1;
                    add(senhaField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    add(usernameLabel, gbc);

                    gbc.gridx = 1;
                    add(usernameField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    add(profilePicLabel, gbc);

                    gbc.gridx = 1;
                    add(selectFileButton, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    gbc.gridwidth = 2;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(20, 0, 0, 0); // Add vertical spacing between components
                    add(saveButton, gbc);

                    gbc.gridy = 6;
                    gbc.insets = new Insets(10, 0, 0, 0); // Add vertical spacing between components
                    add(goBackButton, gbc);

                    // Set foreground color to white for all labels and text fields
                    Color whiteColor = Color.WHITE;
                    nameLabel.setForeground(whiteColor);
                    emailLabel.setForeground(whiteColor);
                    senhaLabel.setForeground(whiteColor);
                    usernameLabel.setForeground(whiteColor);
                    profilePicLabel.setForeground(whiteColor);
                    nameField.setForeground(whiteColor);
                    emailField.setForeground(whiteColor);
                    senhaField.setForeground(whiteColor);
                    usernameField.setForeground(whiteColor);

                    setVisible(true);
                    setLocationRelativeTo(null);
                } else {
                    throw new MyCustomException("Session undefined");
                }

            } catch (MyCustomException e) {
                System.out.println(e.getMessage());
                dispose();
            }
        }

        public void saveProfile() {
            String name = nameField.getText();
            String email = emailField.getText();
            String senha = senhaField.getText();
            String username = usernameField.getText();
            String profilePic = profilePicField.getText();
            
            // Perform saving logic or update the User object
            // ...
        }

        public void descartar() {
            dispose();
        }

        public static void main(String[] args) {
            JSONObject a = new JSONObject();
            Perfil p = new Perfil(a);
        }
    }

