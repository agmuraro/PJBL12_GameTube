package src;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDesejos extends JFrame {
    private JSONObject session;
    public ListaDesejos(JSONObject session){
        this.session = session;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuBiblioteca = new JMenu("Biblioteca");
        JMenu menuLoja = new JMenu("Loja");
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
        JMenuItem verLoja = new JMenuItem("Ver Loja");
        verLoja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Loja(session).setVisible(true);
            }
        });

        menuBiblioteca.add(verJogos);
        menuPerfil.add(irPerfil);
        menuLoja.add(verLoja);

        barraMenu.add(menuBiblioteca);
        barraMenu.add(menuPerfil);
        barraMenu.add(menuLoja);

        getContentPane().add(BorderLayout.NORTH, barraMenu);
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
