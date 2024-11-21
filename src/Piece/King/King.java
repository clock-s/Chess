package Piece.King;

import Piece.Piece;
import Piece.Coord;
import Table.Table;

import javax.swing.*;

public class King extends Piece {
    public King(char color, ImageIcon icon, Coord coord, Table table) {
        setType('K');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    private boolean isInLimit(byte i, byte j, byte size){
        if(coord.i + i >= size || coord.i + i < 0){
            return false;
        }
        if(coord.j + j >= size || coord.j + j < 0){
            return false;
        }
        return true;
    }

    @Override
    public Coord[] move() {
        byte size = (byte) getTable().length;
        Coord[] movements = new Coord[8];
        this.index = 0;

        for(byte i = -1 ; i <= 1 ; ++i){
            for(byte j = -1; j <= 1; ++j){
                if(i == 0 && j == 0){
                    continue;
                }
                if(isInLimit(i,j,size)){
                    putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + j));
                }
            }
        }

        return movements;
    }



    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
