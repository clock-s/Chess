package Piece.Knight;

import Piece.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(char color, ImageIcon icon, Coord coord, Table table){
        setType('K');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    public Coord[] move(){
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[8];
        byte index = 0;



        if(getCoord().i - 2 >= 0){
            if(getCoord().j - 1 >= 0){
                if (getTable()[getCoord().i - 2][getCoord().j - 1] == null || getTable()[getCoord().i - 2][getCoord().j - 1].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i - 2, getCoord().j - 1);
                }
            }
            if(getCoord().j + 1 < size){
                if (getTable()[getCoord().i - 2][getCoord().j + 1] == null || getTable()[getCoord().i - 2][getCoord().j + 1].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i - 2, getCoord().j + 1);
                }
            }
        }
        if(getCoord().i + 2 < size){
            if(getCoord().j - 1 >= 0){
                if (getTable()[getCoord().i + 2][getCoord().j - 1] == null || getTable()[getCoord().i + 2][getCoord().j - 1].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i + 2, getCoord().j - 1);
                }
            }
            if(getCoord().j + 1 < size){
                if (getTable()[getCoord().i + 2][getCoord().j + 1] == null ||getTable()[getCoord().i + 2][getCoord().j + 1].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i + 2, getCoord().j + 1);
                }
            }
        }

        if(getCoord().j - 2 >= 0){
            if(getCoord().i - 1 >= 0){
                if (getTable()[getCoord().i - 1][getCoord().j - 2] == null || getTable()[getCoord().i - 1][getCoord().j - 2].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i - 1, getCoord().j - 2);
                }
            }
            if(getCoord().i + 1 < size){
                if (getTable()[getCoord().i + 1][getCoord().j - 2] == null || getTable()[getCoord().i + 1][getCoord().j - 2].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i + 1, getCoord().j - 2);
                }
            }
        }
        if(getCoord().j + 2 < size){
            if(getCoord().i - 1 >= 0){
                if (getTable()[getCoord().i - 1][getCoord().j + 2] == null || getTable()[getCoord().i - 1][getCoord().j + 2].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i - 1, getCoord().j + 2);
                }
            }
            if(getCoord().i + 1 < size){
                if (getTable()[getCoord().i + 1][getCoord().j + 2] == null || getTable()[getCoord().i + 1][getCoord().j + 2].getColor() != getColor()) {
                    movements[index++] = new Coord(getCoord().i + 1, getCoord().j + 2);
                }
            }
        }

        return movements;
    }
    public Coord[] dangerZone(){
        return move();
    }
}
