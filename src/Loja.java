package src;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loja extends JFrame{
    private JSONObject session;
    public Loja(JSONObject session){
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
                JMenu menuBiblioteca = new JMenu("Biblioteca");
                JMenu menuLista = new JMenu("Lista de Desejos");
                JMenu menuPerfil = new JMenu("Perfil");

                JMenuItem verJogos = new JMenuItem("Ver Jogos");
                verJogos.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Biblioteca(session).setVisible(true);
                    }
                });
                JMenuItem irPerfil = new JMenuItem("Ir para o Perfil");
                irPerfil.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Perfil(session).setVisible(true);
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
                menuPerfil.add(irPerfil);
                menuLista.add(verLista);

                barraMenu.add(menuBiblioteca);
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
