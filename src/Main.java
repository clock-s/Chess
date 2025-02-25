import Table.Table;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int tableSize = (int)(screenSize.getHeight()*0.6);

        // Criar a janela principal
        JFrame window = new JFrame("Xadrez em Java");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(tableSize, tableSize);
        window.setLayout(new CardLayout());
        window.setIconImage(new ImageIcon("src/GUI/Images/logo.jpg").getImage());

        // Criar o painel principal com CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());

        // Criar os painéis para cada "tela"
        JPanel menuPanel = criarPainelMenu(mainPanel);
        JPanel infoPanel = criarPainelInformacoes(mainPanel);
        Table gamePanel = new Table();
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(infoPanel, "Informacoes");
        mainPanel.add(gamePanel, "Jogo");  // Aqui adicionamos o jogo// Usa o painel do jogo já existente

        // Adicionar os painéis ao CardLayout
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(infoPanel, "Informacoes");
        mainPanel.add(gamePanel, "Jogo");

        window.add(mainPanel);
        window.setVisible(true);
    }

    private static JPanel criarPainelMenu(JPanel mainPanel) {
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

        JButton button2 = new JButton("Informações");
        button2.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painel.add(button2, gbc);

        // Ação do botão "Jogar"
        button1.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            layout.show(mainPanel, "Jogo");
        });

        // Ação do botão "Informações"
        button2.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            layout.show(mainPanel, "Informacoes");
        });

        return painel;
    }

    private static JPanel criarPainelInformacoes(JPanel mainPanel) {
        JPanel infoPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
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
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainPanel.getLayout();
            layout.show(mainPanel, "Menu");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);

        return infoPanel;
    }
}