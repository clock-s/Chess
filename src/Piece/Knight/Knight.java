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



        if(coord.i - 2 >= 0){
            if(coord.j - 1 >= 0){
                if (getTable()[coord.i - 2][coord.j - 1] == null || getTable()[coord.i - 2][coord.j - 1].getColor() != color) {
                    movements[index++] = new Coord(coord.i - 2, coord.j - 1);
                }
            }
            if(coord.j + 1 < size){
                if (getTable()[coord.i - 2][coord.j + 1] == null || getTable()[coord.i - 2][coord.j + 1].getColor() != color) {
                    movements[index++] = new Coord(coord.i - 2, coord.j + 1);
                }
            }
        }
        if(coord.i + 2 < size){
            if(coord.j - 1 >= 0){
                if (getTable()[coord.i + 2][coord.j - 1] == null || getTable()[coord.i + 2][coord.j - 1].getColor() != color) {
                    movements[index++] = new Coord(coord.i + 2, coord.j - 1);
                }
            }
            if(coord.j + 1 < size){
                if (getTable()[coord.i + 2][coord.j + 1] == null ||getTable()[coord.i + 2][coord.j + 1].getColor() != color) {
                    movements[index++] = new Coord(coord.i + 2, coord.j + 1);
                }
            }
        }

        if(coord.j - 2 >= 0){
            if(coord.i - 1 >= 0){
                if (getTable()[coord.i - 1][coord.j - 2] == null || getTable()[coord.i - 1][coord.j - 2].getColor() != color) {
                    movements[index++] = new Coord(coord.i - 1, coord.j - 2);
                }
            }
            if(coord.i + 1 < size){
                if (getTable()[coord.i + 1][coord.j - 2] == null || getTable()[coord.i + 1][coord.j - 2].getColor() != color) {
                    movements[index++] = new Coord(coord.i + 1, coord.j - 2);
                }
            }
        }
        if(coord.j + 2 < size){
            if(coord.i - 1 >= 0){
                if (getTable()[coord.i - 1][coord.j + 2] == null || getTable()[coord.i - 1][coord.j + 2].getColor() != color) {
                    movements[index++] = new Coord(coord.i - 1, coord.j + 2);
                }
            }
            if(coord.i + 1 < size){
                if (getTable()[coord.i + 1][coord.j + 2] == null || getTable()[coord.i + 1][coord.j + 2].getColor() != color) {
                    movements[index++] = new Coord(coord.i + 1, coord.j + 2);
                }
            }
        }

        return movements;
    }
    public Coord[] dangerZone(){
        return move();
    }
}
