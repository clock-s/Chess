package Piece;

import Table.Table;

import javax.swing.*;

public abstract class Piece {
    private int moves = 0;
    private char color;
    private char type;
    private Coord coord;
    private Table table;
    private ImageIcon icon;

    public abstract Coord[] move();
    public abstract Coord[] dangerZone();

    public Piece(){

    }


    public ImageIcon getIcon() {
        return icon;
    }
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Piece[][] getTable() {
        return table.getMap();
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
    public Coord getCoord() {
        return coord;
    }

    public void incrementMoves() {
        moves++;
    }
    public int getMoves(){
        return moves;
    }

    public void setColor(char color) {
        this.color = color;
    }
    public char getColor() {
        return color;
    }

    public void setType(char type) {
        this.type = type;
    }
    public char getType(){
        return type;
    }
}



