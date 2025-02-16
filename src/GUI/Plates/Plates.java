package GUI.Plates;

import GUI.GUI;
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
    private boolean isEnable = true;


    public void resetClick(){
        isClicked = false;
    }

    public Plates(Color color, Coord coord, GUI gui) {
        this.panel = new JPanel();
        this.coord = new Coord(coord.i,coord.j);
        this.icon = new ImageIcon();
        piece = new JLabel(icon);
        this.gui = gui;
        this.panel.setBackground(color);

        panel.addMouseListener(this);
        panel.add(piece);
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
        for(Coord p: potentialMoves) {
            gui.modifyColor(p, Color.BLUE);
        }
    }

    private void disableMoves(Coord coord){
        Coord[] moves = gui.getPiece(coord).move();
        Coord[] potentialMoves = gui.getPiece(coord).getPotentialDangerZone();

        for(Coord m : moves) {
            System.out.printf("(%d,%d) |",m.i,m.j);
            Color color = ((m.i+m.j)%2 == 0) ? gui.getColor1() : gui.getColor2();
            gui.modifyColor(m, color);
        }

        for(Coord p : potentialMoves) {
            Color color = ((p.i+p.j)%2 == 0) ? gui.getColor1() : gui.getColor2();
            gui.modifyColor(p, color);
        }

        System.out.println("");
    }

    private boolean isTheSameColor(Coord[] coord){
        if(coord[0].isEquals(new Coord()) || coord[1].isEquals(new Coord())) {
            return false;
        }
        return (gui.getPiece(coord[0]) != null && gui.getPiece(coord[1]) != null) &&
                (gui.getPiece(coord[0]).getColor() == gui.getPiece(coord[1]).getColor());
    }

    public void setIsEnable(boolean enable){
        isEnable = enable;
    }

    private boolean isCanMove(Coord piece,  Coord goal){
        if(piece.isEquals(new Coord())){
            return false;
        }
        Coord[] moves = (gui.getPiece(piece) != null) ? gui.getPiece(piece).move() : null;

        if(moves != null) {
            for (Coord m : moves) {
                if (m != null) {
                    if (m.isEquals(goal)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void move(){
        Coord[] coord = gui.clicks;
        byte count = gui.count;


        if(gui.getPiece(this.coord) != null && gui.getPlate(this.coord).isEnable == true) {
            if(coord[0].isEquals(new Coord()) || coord[1].isEquals(new Coord()) || this.coord.isEquals(coord[count])) {
                if(isClicked == false){
                    isClicked = !isClicked;
                    showMoves();
                } else{
                    isClicked = !isClicked;
                    disableMoves(this.coord);
                }
            }else{
                if(isTheSameColor(coord)){
                    gui.getPlate(coord[count]).disableMoves(coord[count]);
                    showMoves();
                    isClicked = !isClicked;
                }
            }
        }else{
            if(isCanMove(coord[count], this.coord)) {
                disableMoves(coord[count]);
                gui.getTable().newPiecePosition(coord[count], this.coord);
                isClicked = !isClicked;
            }else{
                return;
            }
        }

        gui.countIncrease();
        gui.clicks[gui.count].setCoord(this.coord);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        move();
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
