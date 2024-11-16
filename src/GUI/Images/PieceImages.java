package GUI.Images;


import javax.swing.*;
import java.awt.*;

public class PieceImages {
    private int size;

    public ImageIcon WPeon;
    public ImageIcon WKnight;
    public ImageIcon WRook;
    public ImageIcon WBishop;
    public ImageIcon WQueen;
    public ImageIcon WKing;

    public ImageIcon BPeon;
    public ImageIcon BKnight;
    public ImageIcon BRook;
    public ImageIcon BQueen;
    public ImageIcon BKing;
    public ImageIcon BBishop;

    public PieceImages(int imageSize){
        size = imageSize - 5;

        WPeon = new ImageIcon("src/GUI/Images/WPeon.png");
        WPeon.setImage(WPeon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

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

        BPeon = new ImageIcon("src/GUI/Images/BPeon.png");
        BPeon.setImage(BPeon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));

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
