package GUI.Images;


import GUI.GUI;

import javax.swing.*;
import java.awt.*;

public class PieceImages {
    private int size;

    public static ImageIcon WPawn;
    public static ImageIcon WKnight;
    public static ImageIcon WRook;
    public static ImageIcon WBishop;
    public static ImageIcon WQueen;
    public static ImageIcon WKing;

    public static ImageIcon BPawn;
    public static ImageIcon BKnight;
    public static ImageIcon BRook;
    public static ImageIcon BQueen;
    public static ImageIcon BKing;
    public static ImageIcon BBishop;

    public PieceImages(){
        size = GUI.getImageSize() - 5;

        WPawn = new ImageIcon("src/GUI/Images/WPeon.png");
        WPawn.setImage(WPawn.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        WKnight = new ImageIcon("src/GUI/Images/WKnight.png");
        WKnight.setImage(WKnight.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        WRook = new ImageIcon("src/GUI/Images/WRook.png");
        WRook.setImage(WRook.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        WBishop = new ImageIcon("src/GUI/Images/WBishop.png");
        WBishop.setImage(WBishop.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        WQueen = new ImageIcon("src/GUI/Images/WQueen.png");
        WQueen.setImage(WQueen.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        WKing = new ImageIcon("src/GUI/Images/WKing.png");
        WKing.setImage(WKing.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        //==============================================================================================//

        BPawn = new ImageIcon("src/GUI/Images/BPeon.png");
        BPawn.setImage(BPawn.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        BKnight = new ImageIcon("src/GUI/Images/BKnight.png");
        BKnight.setImage(BKnight.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        BRook = new ImageIcon("src/GUI/Images/BRook.png");
        BRook.setImage(BRook.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        BBishop = new ImageIcon("src/GUI/Images/BBishop.png");
        BBishop.setImage(BBishop.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        BQueen = new ImageIcon("src/GUI/Images/BQueen.png");
        BQueen.setImage(BQueen.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

        BKing = new ImageIcon("src/GUI/Images/BKing.png");
        BKing.setImage(BKing.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

}
