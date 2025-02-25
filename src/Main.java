import Table.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Xadrez em Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Xadrez dos crias");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(label, gbc);

        JButton button1 = new JButton("Jogar");
        button1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        painel.add(button1, gbc);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Table table = new Table();
            }
        });

        JButton button2 = new JButton("Informações");
        button2.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painel.add(button2, gbc);

        // Ação do botão "Informações"
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame infoFrame = new JFrame("Informações");
                infoFrame.setSize(400, 300);
                infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                // Texto inicial
                textArea.setText("O xadrez é jogado em um tabuleiro de 8x8, com as peças posicionadas de forma específica no início do jogo. \n" +
                        "Cada jogador tem 16 peças: 1 rei, 1 dama, 2 torres, 2 cavalos, 2 bispos e 8 peões. \n" +
                        "O objetivo é capturar o rei adversário, colocando-o em \"xeque-mate\". \n" +
                        "As peças se movem de maneira específica: \n" +
                        "- O rei se move uma casa em qualquer direção. \n" +
                        "- A dama se move em linha reta (horizontal, vertical ou diagonal). \n" +
                        "- A torre se move em linha reta (horizontal ou vertical). \n" +
                        "- O bispo se move diagonalmente. \n" +
                        "- O cavalo se move em forma de \"L\" (duas casas em uma direção e uma em ângulo reto). \n" +
                        "- Os peões se movem uma casa para frente, mas capturam na diagonal. \n");

                JScrollPane scrollPane = new JScrollPane(textArea);
                infoFrame.add(scrollPane);

                infoFrame.setVisible(true);

                infoFrame.setVisible(true);
            }
        });

        frame.add(painel);
        frame.setVisible(true);
    }}