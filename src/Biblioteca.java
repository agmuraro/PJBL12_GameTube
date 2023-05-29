package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Biblioteca extends JFrame {


    public Biblioteca() {

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

        JFrame frame = new JFrame("GameTube");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(label);
        frame.setVisible(true);
        frame.add(labelDois);


        JMenuBar barraMenu = new JMenuBar();
        JMenu menuLoja = new JMenu ("Loja");
        JMenu menuLista = new JMenu("Lista de Desejos");
        JMenu menuPerfil = new JMenu("Perfil");

        JMenuItem irPerfil = new JMenuItem("Ir para o Perfil");
        irPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Perfil().setVisible(true);
            }
        });


        menuPerfil.add(irPerfil);

        barraMenu.add(menuLoja);
        barraMenu.add(menuLista);
        barraMenu.add(menuPerfil);

        frame.getContentPane().add(BorderLayout.NORTH, barraMenu);
        frame.setVisible(true);


    }
}

