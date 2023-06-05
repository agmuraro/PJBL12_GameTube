package src;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Biblioteca extends JFrame {
    private JSONObject session;

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
                ImageIcon posterUm = new ImageIcon("image/img_1.png");

                JLabel label = new JLabel();
                label.setText("Sonic Mania");
                label.setIcon(posterUm);
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.BOTTOM);
                label.setForeground(Color.WHITE);
                label.setIconTextGap(20);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);

                ImageIcon posterDois = new ImageIcon("image/img_3.png");

                JLabel labelDois = new JLabel();
                labelDois.setText("Portal 2");
                labelDois.setIcon(posterDois);
                labelDois.setHorizontalTextPosition(JLabel.CENTER);
                labelDois.setVerticalTextPosition(JLabel.BOTTOM);
                labelDois.setForeground(Color.WHITE);
                labelDois.setIconTextGap(20);
                labelDois.setVerticalAlignment(JLabel.CENTER);
                labelDois.setHorizontalAlignment(JLabel.LEFT);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1000, 700);
                getContentPane().setBackground(Color.DARK_GRAY);
                add(label);
                setVisible(true);
                add(labelDois);


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

    }

    public void descartar() {
        dispose();
    }
}

