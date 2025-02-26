package GUI.Plates;

import GUI.GUI;
import Piece.King;
import Table.Table;
import Utilities.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plates implements MouseListener {
    private Coord coord;
    private JPanel panel;
    private JLabel piece;
    private ImageIcon icon;
    private GUI gui;
    private boolean isClicked;
    private boolean isEnable = false;


    public Plates(Color color, Coord coord, GUI gui) {
        this.panel = new JPanel();
        this.coord = new Coord(coord.i,coord.j);
        this.icon = new ImageIcon();
        this.piece = new JLabel(icon, SwingConstants.CENTER);
        this.gui = gui;
        this.panel.setBackground(color);
        this.panel.setLayout(new BorderLayout());

        panel.addMouseListener(this);
        panel.add(piece, BorderLayout.CENTER);
    }



    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        piece.setIcon(this.icon);
    }

    public JPanel getPanel() {
        return panel;
    }

    public Coord getCoord() {
        return coord;
    }

    private void showMoves(){
        Coord[] moves = gui.getPiece(coord).getMovements();
        Coord[] potentialMoves = gui.getPiece(coord).getPotentialDangerZone();

        for(Coord m : moves) {
            gui.modifyColor(m, Color.RED);
        }
        /*
        for(Coord p: potentialMoves) {
            gui.modifyColor(p, Color.BLUE);
        }
        */
    }

    private void disableMoves(){
        Coord[] moves = gui.getPiece(gui.clicks[gui.count]).getMovements();
        Coord[] potentialMoves = gui.getPiece(gui.clicks[gui.count]).getPotentialDangerZone();

        for(Coord p : potentialMoves) {
            Color color = ((p.i+p.j)%2 == 0) ? gui.getColor1() : gui.getColor2();
            gui.modifyColor(p, color);
        }

        for(Coord m : moves) {
            Color color = ((m.i+m.j)%2 == 0) ? gui.getColor1() : gui.getColor2();
            gui.modifyColor(m, color);
        }


    }



    public void setIsEnable(boolean enable){
        isEnable = enable;
    }

    private boolean isCanMove(Coord piece,  Coord goal){
        if(piece.isEquals(new Coord())){
            return false;
        }
        if(gui.getPiece(piece).getColor() != Table.getRound()) return false;
        Coord[] moves = gui.getPiece(piece).getMovements();


        for(Coord m : moves) {
            if(m.isEquals(goal) && !(gui.getPiece(m) instanceof King)) {
                return true;
            }
        }

        return false;
    }

    public void move(){
        boolean attClicks = false;


        if(gui.getPiece(this.coord) != null){
            if(gui.getPiece(this.coord).getColor() == Table.getRound()) {
                if(gui.clicks[gui.count].isEquals(new Coord()) || gui.clicks[gui.count].isEquals(this.coord)) {

                    if(isClicked == false){
                        isClicked = true;
                        showMoves();
                    } else{
                        isClicked = false;
                        disableMoves();
                    }
                    attClicks = true;
                }else if(gui.getPiece(gui.clicks[gui.count]) != null) {
                    disableMoves();
                    gui.getPlate(gui.clicks[gui.count]).isClicked = false;
                    showMoves();
                    isClicked = true;
                    attClicks = true;
                }
            }else if(gui.getPiece(this.coord).getColor() != Table.getRound()){
                if(isCanMove(gui.clicks[gui.count], this.coord)){
                    disableMoves();
                    isClicked = false;
                    gui.getPlate(gui.clicks[gui.count]).isClicked = false;
                    gui.getTable().newPiecePosition(gui.clicks[gui.count], this.coord);
                    attClicks = true;
                }
            }
        }else if(gui.getPiece(this.coord) == null){
            if(isCanMove(gui.clicks[gui.count], this.coord)){
                disableMoves();
                isClicked = false;
                gui.getPlate(gui.clicks[gui.count]).isClicked = false;
                gui.getTable().newPiecePosition(gui.clicks[gui.count], this.coord);
                attClicks = true;
            }
        }


        if(attClicks) {
            gui.countIncrease();
            gui.clicks[gui.count].setCoord(this.coord);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isEnable) move();
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
