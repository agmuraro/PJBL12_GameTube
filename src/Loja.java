package src;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loja extends JFrame{
    private JSONObject session;
    public Loja(JSONObject session){
        JFrame frame = new JFrame("GameTube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuBiblioteca = new JMenu("Biblioteca");
        JMenu menuLista = new JMenu("Lista de Desejos");
        JMenu menuPerfil = new JMenu("Perfil");

        JMenuItem verJogos = new JMenuItem("Ver Jogos");
        verJogos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Biblioteca(session).setVisible(true);
            }
        });
        JMenuItem irPerfil = new JMenuItem("Ir para o Perfil");
        irPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Perfil(session).setVisible(true);
            }
        });

        JMenuItem verLista = new JMenuItem("Ver Lista");
        verLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ListaDesejos(session).setVisible(true);
            }
        });

        menuBiblioteca.add(verJogos);
        menuPerfil.add(irPerfil);
        menuLista.add(verLista);

        barraMenu.add(menuBiblioteca);
        barraMenu.add(menuLista);
        barraMenu.add(menuPerfil);

        frame.getContentPane().add(BorderLayout.NORTH, barraMenu);
        frame.setVisible(true);

    }

}
