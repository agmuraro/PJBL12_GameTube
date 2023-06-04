package src;


import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class Perfil extends JFrame {

    private JSONObject session;

    public Perfil(JSONObject session) {
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

//        this.session = session;
    try {
        if (session.has("name")){
            ImageIcon image = new ImageIcon("image/img_2.png");

            JLabel nomeLabel = new JLabel();
            nomeLabel.setText(session.getString("name"));
            nomeLabel.setIcon(image);
            nomeLabel.setHorizontalTextPosition(JLabel.CENTER);
            nomeLabel.setVerticalTextPosition(JLabel.BOTTOM);
            nomeLabel.setForeground(Color.WHITE);
            nomeLabel.setIconTextGap(20);
            nomeLabel.setVerticalAlignment(JLabel.CENTER);
            nomeLabel.setHorizontalAlignment(JLabel.CENTER);


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1000, 700);
            getContentPane().setBackground(Color.DARK_GRAY);
            add(nomeLabel);
            setVisible(true);

            JMenuBar barraMenu = new JMenuBar();
            JMenu menuBiblioteca = new JMenu("Biblioteca");
            JMenu menuLoja = new JMenu("Loja");
            JMenu menuLista = new JMenu("Lista de Desejos");

            JMenuItem verJogos = new JMenuItem("Ver Jogos");
            verJogos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Biblioteca(session).setVisible(true);
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


            menuBiblioteca.add(verJogos);
            menuLoja.add(verLoja);
            menuLista.add(verLista);

            barraMenu.add(menuBiblioteca);
            barraMenu.add(menuLoja);
            barraMenu.add(menuLista);

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

    }
    public void descartar() {
        dispose();
    }
    public static void main(String[] args) {
        JSONObject a = new JSONObject();
        Perfil p = new Perfil(a);
    }
}
