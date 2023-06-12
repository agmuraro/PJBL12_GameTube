package src;

import org.json.JSONObject;
import org.json.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFrame;

public class Biblioteca extends JFrame {
    private JSONObject session;
    private JPanel gamePanelContainer;

    public Biblioteca(JSONObject session) {
        this.session = session;
        if (!session.has("name")) {
            JOptionPane optionPane = new JOptionPane("Por favor realize login", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);

            JButton customButton = new JButton("Fechar");

            optionPane.setOptions(new Object[] { customButton });

            // Create a dialog with the option pane
            JDialog dialog = optionPane.createDialog("No Session");

            // Add an action listener to the custom button
            customButton.addActionListener(e -> {
                descartar();
                dialog.dispose();
            });

            // Set the dialog to be modal
            dialog.setModal(true);

            // Set the dialog to be non-resizable
            dialog.setResizable(false);

            // Display the dialog
            dialog.setVisible(true);
        }
        try {
            if (session.has("name")){

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1000, 700);
                getContentPane().setBackground(Color.DARK_GRAY);
                setVisible(true);


                JMenuBar barraMenu = new JMenuBar();
                JMenu menuLoja = new JMenu ("Loja");
                JMenu menuLista = new JMenu("Lista de Desejos");
                JMenu menuPerfil = new JMenu("Perfil");

                JMenuItem irPerfil = new JMenuItem("Ir para o Perfil");
                irPerfil.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Perfil(session).setVisible(true);
                    }
                });
                JMenuItem verLoja = new JMenuItem("Ver Loja");
                verLoja.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Loja(session).setVisible(true);
                    }
                });

                JMenuItem verLista = new JMenuItem("Ver Lista");
                verLista.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new ListaDesejos(session).setVisible(true);
                    }

                });


                menuPerfil.add(irPerfil);
                menuLoja.add(verLoja);
                menuLista.add(verLista);

                barraMenu.add(menuLoja);
                barraMenu.add(menuLista);
                barraMenu.add(menuPerfil);

                getContentPane().add(BorderLayout.NORTH, barraMenu);
                setVisible(true);
                setLocationRelativeTo(null);
            } else {
                throw new MyCustomException("Session undefined");
            }

        } catch (MyCustomException e) {
            System.out.println(e.getMessage());
            descartar();
        }
        readGameListingsFromFile("src/games.json");
    }

    private void readGameListingsFromFile(String filePath) {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray gameListingsArray = new JSONArray(fileContent);

            // Initialize the game panel container with a grid layout
            gamePanelContainer = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, 10px horizontal and vertical gaps
            gamePanelContainer.setBackground(Color.DARK_GRAY); // Set background color to dark gray

            // Add an empty filler panel for padding on the left side
            JPanel fillerPanel = new JPanel();
            fillerPanel.setOpaque(false);
            gamePanelContainer.add(fillerPanel);

            for (int i = 0; i < gameListingsArray.length(); i++) {
                JSONObject listingObject = gameListingsArray.getJSONObject(i);
                String imagePath = listingObject.getString("directory");
                String name = listingObject.getString("name");

                // Load the image from the file
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                // Create a panel to hold the image and name for each game
                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
                gamePanel.setBackground(Color.DARK_GRAY); // Set background color to dark gray

                // Create a JLabel to display the image
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(scaledImageIcon);
                gamePanel.add(imageLabel);

                // Create a panel to hold the name
                JPanel namePanel = new JPanel();
                namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
                namePanel.setBackground(Color.DARK_GRAY); // Set background color to dark gray

                // Create a JLabel to display the name
                JLabel nameLabel = new JLabel(name);
                nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                nameLabel.setForeground(Color.WHITE); // Set text color to white
                namePanel.add(nameLabel);

                gamePanel.add(namePanel);

                // Add the game panel to the game panel container
                gamePanelContainer.add(gamePanel);

                // Add a MouseListener to the image label
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Handle the image click event here
                    }
                });
            }

            // Add the game panel container to the content pane
            getContentPane().add(gamePanelContainer, BorderLayout.CENTER);

            // Update the content pane layout after adding all game panels
            getContentPane().revalidate();
            getContentPane().repaint();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }




    public void descartar() {
        dispose();
    }
}
