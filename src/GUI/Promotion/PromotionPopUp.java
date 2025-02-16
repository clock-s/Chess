package GUI.Promotion;

import GUI.Images.PieceImages;
import Player.Player;
import Table.Table;
import Utilities.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PromotionPopUp extends JDialog implements MouseListener, ActionListener {
    JPopupMenu PromotionMenu;
    JLabel PromotionLabel;
    JPanel choosePieces;
    ChoiceButton bishop;
    ChoiceButton rook;
    ChoiceButton knight;
    ChoiceButton queen;
    public Table table;


    public PromotionPopUp(JFrame parent) {
        // Define o JDialog como modal e associa ao JFrame pai
        super(parent, "Promotion", true);
        setSize((int)(parent.getSize().width * 0.6), (int)(parent.getSize().height * 0.2));
        //setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());
        this.setUndecorated(true);

        PromotionMenu = new JPopupMenu();
        PromotionLabel = new JLabel("Escolha uma peça para promoção:");
        PromotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(PromotionLabel, BorderLayout.NORTH);

        choosePieces = new JPanel(new GridLayout(1, 4));
        add(choosePieces, BorderLayout.CENTER);

        bishop = new ChoiceButton("Bishop", PieceImages.BBishop);
        choosePieces.add(bishop);


        knight = new ChoiceButton("Knight",PieceImages.BKnight);
        choosePieces.add(knight);

        rook = new ChoiceButton("Rook",PieceImages.BRook);
        choosePieces.add(rook);

        queen = new ChoiceButton("Queen", PieceImages.BQueen);
        choosePieces.add(queen);

        bishop.addActionListener(this);
        knight.addActionListener(this);
        rook.addActionListener(this);
        queen.addActionListener(this);




        this.addMouseListener(this);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Mostra o menu popup no local do clique
        PromotionMenu.show(this, e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent a) {
        // Define a escolha no objeto Table e fecha o diálogo
        if (a.getSource() == bishop) {
            Player.setChoice(Category.BISHOP);
        } else if (a.getSource() == rook) {
            Player.setChoice(Category.ROOK);
        } else if (a.getSource() == knight) {
            Player.setChoice(Category.KNIGHT);
        } else if (a.getSource() == queen) {
            Player.setChoice(Category.QUEEN);
        }
        dispose(); // Fecha o diálogo
    }
}
