package Piece;

import Table.Table;

import javax.swing.*;

public abstract class Piece {
    protected boolean isAlive = true;
    protected byte id;
    protected char color;
    protected char type;
    protected Coord coord;
    protected Table table;
    private ImageIcon icon;
    private int moves = 0;
    protected byte index = 0;
    protected byte numOfMoves;


    public Piece(byte id, char color, ImageIcon icon, Coord coord, Table table){
        this.id = id;
        this.color = color;
        this.icon = icon;
        this.coord = coord;
        this.table = table;
    }


    public abstract Coord[] move();
    public abstract Coord[] dangerZone();

    public byte getId(){
        return id;
    }

    public boolean isAlive(){
        return isAlive;
    }
    public void death(){
        isAlive = false;
    }

    public byte getNumOfMoves(){
        return numOfMoves;
    }

    protected boolean putMoves(Coord[] movements, byte i, byte j){
        if(getTable()[i][j] == null){
            movements[this.index++] = new Coord(i,j);
        }else{
            if(getTable()[i][j].getColor() == color){
                return false;
            }else{
                movements[this.index++] = new Coord(i,j);
                return false;
            }
        }
        return true;
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



