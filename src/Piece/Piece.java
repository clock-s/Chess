package Piece;

import Collection.MoveList;
import Table.Table;
import Utilities.*;

import javax.swing.*;

public abstract class Piece {
    protected boolean isAlive = true;
    protected byte id;
    protected Color color;
    protected Category category;
    protected Coord coord;
    protected Table table;
    private ImageIcon icon;
    private int moves = 0;
    protected byte numOfMoves;

    protected MoveList movements = new MoveList();
    protected MoveList dangerZone = new MoveList();
    protected MoveList potentialDangerZone = new MoveList();


    public Piece(byte id, Color color, ImageIcon icon, Coord coord, Table table){
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

    protected boolean putMoves(MoveList movements, byte i, byte j){
        if(getTable()[i][j] == null){
            movements.add(new Coord(i,j));
        }else{
            if(getTable()[i][j].getColor() == color){
                return false;
            }else{
                movements.add(new Coord(i,j));
                return false;
            }
        }
        return true;
    }

    protected boolean isInLimit(int i, int j, byte size){
        if(coord.i + i >= size || coord.i + i < 0){
            return false;
        }
        if(coord.j + j >= size || coord.j + j < 0){
            return false;
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

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public Category getCategory(){
        return this.category;
    }
}



