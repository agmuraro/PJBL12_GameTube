package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDesejos extends JFrame {

    public ListaDesejos(){
        JFrame frame = new JFrame("GameTube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuBiblioteca = new JMenu("Biblioteca");
        JMenu menuLoja = new JMenu("Loja");
        JMenu menuPerfil = new JMenu("Perfil");

        JMenuItem verJogos = new JMenuItem("Ver Jogos");
        verJogos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Biblioteca().setVisible(true);
            }
        });
        JMenuItem irPerfil = new JMenuItem("Ir para o Perfil");
        irPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Perfil().setVisible(true);
            }
        });
        JMenuItem verLoja = new JMenuItem("Ver Loja");
        verLoja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Loja().setVisible(true);
            }
        });

        menuBiblioteca.add(verJogos);
        menuPerfil.add(irPerfil);
        menuLoja.add(verLoja);

        barraMenu.add(menuBiblioteca);
        barraMenu.add(menuPerfil);
        barraMenu.add(menuLoja);

        frame.getContentPane().add(BorderLayout.NORTH, barraMenu);
        frame.setVisible(true);
    }

}
