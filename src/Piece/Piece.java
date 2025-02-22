package Piece;

import Collection.MoveList;
import Table.Table;
import Utilities.*;
import Table.EsqueletonPlate;

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

    public Coord[] getMovements() {return move();}
    public Coord[] getDangerZone() {return dangerZone();}
    public Coord[] getPotentialDangerZone() {
        dangerZone();
        return (Coord[])potentialDangerZone.toArray();
    }

    public byte getId(){
        return id;
    }

    public boolean isAlive(){
        return isAlive;
    }
    public void death(){
        isAlive = false;
    }



    protected boolean putMoves(MoveList movements, byte i, byte j){
        if(seeOtherTable(i,j).getPiece() == null){
            movements.add(new Coord(i,j));
        }else{
            if(seeOtherTable(i,j).getPiece().getColor() == color){
                potentialDangerZone.add(new Coord(i,j));
                return false;
            }else{
                movements.add(new Coord(i,j));
                return false;
            }
        }
        return true;
    }

    protected boolean isInLimit(int i, int j){
        if(coord.i + i >= Table.LENGHT || coord.i + i < 0){
            return false;
        }
        if(coord.j + j >= Table.LENGHT || coord.j + j < 0){
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

    public EsqueletonPlate seeOtherTable(int i, int j) {
        return table.getMap()[i][j];
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

    public String categoryToString(){
        String flag = "";
        if (this.category == Category.ROOK){
            flag = "R";
        }
        if (this.category == Category.KNIGHT){
            flag = "N";
        }
        if (this.category == Category.QUEEN){
            flag = "Q";
        }
        if (this.category == Category.BISHOP){
            flag = "B";
        }
        if (this.category == Category.KING){
            flag = "K";
        }
        return flag;
    }
}



