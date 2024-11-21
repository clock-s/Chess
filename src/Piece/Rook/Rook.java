package Piece.Rook;

import Piece.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    public Rook(char color, ImageIcon icon, Coord coord, Table table){
        setType('R');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    public Rook(char color, Coord coord, Table table){
        setColor(color);
        setCoord(coord);
        setTable(table);
    }

    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[15];
        this.index = 0;

        for(byte i = (byte) (coord.i + 1) ; i < size; ++i){
            if(!putMoves(movements, i, (byte) (coord.j))){
                break;
            }
        }

        for(byte i = (byte) (coord.i - 1) ; i >= 0; --i){
            if(!putMoves(movements, i, (byte) (coord.j))){
                break;
            }
        }

        for(byte j = (byte) (coord.j + 1); j < size ; ++j){
            if(!putMoves(movements, (byte) coord.i, j)){
                break;
            }
        }

        for(byte j = (byte) (coord.j - 1) ; j >= 0 ; --j){
            if(!putMoves(movements, (byte) coord.i, j)){
                break;
            }
        }

        return movements;
    }


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
