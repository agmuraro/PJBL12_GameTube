import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class Perfil extends JFrame {

    public Perfil() {

        ImageIcon image = new ImageIcon("img_2.png");

        JLabel label = new JLabel();
        label.setText("Arthur Muraro");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.WHITE);
        label.setIconTextGap(20);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);


        JFrame frame = new JFrame("GameTube");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(label);
        frame.setVisible(true);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuBiblioteca = new JMenu("Biblioteca");
        JMenu menuLoja = new JMenu ("Loja");
        JMenu menuLista = new JMenu("Lista de Desejos");

        JMenuItem verJogos = new JMenuItem("Ver Jogos");
        verJogos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Biblioteca().setVisible(true);
            }
        });
        menuBiblioteca.add(verJogos);

        barraMenu.add(menuBiblioteca);
        barraMenu.add(menuLoja);
        barraMenu.add(menuLista);

        frame.getContentPane().add(BorderLayout.NORTH, barraMenu);
        frame.setVisible(true);


    }

}
