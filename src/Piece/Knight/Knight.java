package Piece.Knight;

import Piece.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(char color, ImageIcon icon, Coord coord, Table table){
        setType('N');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    public Coord[] move(){
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[8];
        this.index = 0;

        if(coord.i - 2 >= 0){
            if(coord.j - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 2), (byte) (coord.j - 1));
            }
            if(coord.j + 1 < size){
                putMoves(movements,(byte) (coord.i - 2), (byte) (coord.j + 1));
            }
        }
        if(coord.i + 2 < size){
            if(coord.j - 1 >= 0){
                putMoves(movements,(byte) (coord.i + 2), (byte) (coord.j - 1));
            }
            if(coord.j + 1 < size){
                putMoves(movements,(byte) (coord.i + 2), (byte) (coord.j + 1));
            }
        }

        if(coord.j - 2 >= 0){
            if(coord.i - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 1), (byte) (coord.j - 2));
            }
            if(coord.i + 1 < size){
                putMoves(movements,(byte) (coord.i + 1), (byte) (coord.j - 2));
            }
        }
        if(coord.j + 2 < size){
            if(coord.i - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 1), (byte) (coord.j + 2));
            }
            if(coord.i + 1 < size){
                putMoves(movements,(byte) (coord.i + 1), (byte) (coord.j + 2));
            }
        }

        return movements;
    }
    public Coord[] dangerZone(){
        return move();
    }
}
