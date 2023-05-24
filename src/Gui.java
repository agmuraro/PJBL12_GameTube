package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private static JLabel labelImagemJogo;

    public static void main(String args[]) {

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(label);
        frame.setVisible(true);


        ImageIcon icone = new ImageIcon("img.png");
        frame.setIconImage(icone.getImage());

        JMenuBar mb = new JMenuBar();
        JMenu m0 = new JMenu("Perfil");
        JMenu m1 = new JMenu("Biblioteca");
        JMenu m2 = new JMenu("Loja");
        JMenu m3 = new JMenu("Lista de Desejos");


        JMenuItem m12 = new JMenuItem("Ver Jogos");
        JMenuItem m13 = new JMenuItem("Carrinho");
        JMenuItem m22 = new JMenuItem("Promoções");
        JMenuItem m32 = new JMenuItem("Ver Lista");

        m1.add(m12);
        m1.add(m13);
        m2.add(m22);
        m3.add(m32);

        JMenuItem m11 = new JMenuItem("Abrir Biblioteca");
        m1.add(m11);

        JMenuItem m21 = new JMenuItem("Abrir Loja");
        m21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                exibirImagemJogo("img_1.png");
            }
        });
        m2.add(m21);

        JMenuItem m31 = new JMenuItem("Abrir Lista de Desejos");
        m31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                exibirImagemJogo("img_1.png");
            }
        });

        JMenuItem m01 = new JMenuItem("Abrir Perfil");

        m01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setVisible(true);
                labelImagemJogo.setVisible(false);
            }
        });

        m3.add(m31);
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m0);
        m0.add(m01);

        frame.getContentPane().add(BorderLayout.NORTH, mb);

        // JLabel para exibir as imagens dos jogos
        labelImagemJogo = new JLabel();
        frame.getContentPane().add(labelImagemJogo, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void exibirImagemJogo(String caminhoImagem) {
        ImageIcon imagemIcon = new ImageIcon(caminhoImagem);
        Image imagemOriginal = imagemIcon.getImage();
        int larguraMaxima = 500;
        int alturaProporcional = (larguraMaxima * imagemOriginal.getHeight(null)) / imagemOriginal.getWidth(null);
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(larguraMaxima, alturaProporcional, Image.SCALE_SMOOTH);
        ImageIcon imagemRedimensionadaIcon = new ImageIcon(imagemRedimensionada);
        labelImagemJogo.setIcon(imagemRedimensionadaIcon);
    }


}
