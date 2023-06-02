package src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY); // Set the background color of the main panel

        JPanel topPanel = new JPanel(new BorderLayout());

        // Create a custom panel for the profile picture
        JPanel profilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int size = Math.min(getWidth(), getHeight()) - 10;
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;

                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillOval(x, y, size, size);

                // Draw the profile picture
                ImageIcon profileImage = new ImageIcon("image/Logo.png");
                Image img = profileImage.getImage();
                g2d.drawImage(img, x, y, size, size, this);

                g2d.dispose();
            }
        };
        profilePanel.setPreferredSize(new Dimension(150, 106)); // Set the preferred size of the profile panel
        profilePanel.setBackground(Color.LIGHT_GRAY); // Set the background color of the profile panel

        topPanel.add(profilePanel, BorderLayout.CENTER);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        topPanel.add(usernamePanel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        centerPanel.add(loginButton, gbc);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                realizarLogin(usernameField.getText(), passwordField.getText());
            }
        });

        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);

        centerPanel.setBackground(Color.LIGHT_GRAY);
        usernamePanel.setBackground(Color.LIGHT_GRAY);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public  void realizarLogin(String identifier, String code) {
        try{
            String fileContent = new String(Files.readAllBytes(Paths.get("src/usuarios.json")));
            JSONArray jsonArray;
            jsonArray = new JSONArray(fileContent);
            for (Object item : jsonArray) {
                if (item instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) item;
                    try {

                        if (identifier.equals(jsonObject.getString("email")) || identifier.equals(jsonObject.getString("username"))){
                            try {
                                if (jsonObject.getString("senha").equals(code)) {
                                    System.out.println("Logado");
                                } else {
                                    throw new MyCustomException("senha errada");
                                }
                            } catch (MyCustomException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new MyCustomException("email errado");
                        }

                    } catch (MyCustomException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

    }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}




