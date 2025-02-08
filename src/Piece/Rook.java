package Piece;

import Table.Table;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    public Rook(byte id, char color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        setType('R');
        numOfMoves = 15;
    }

    public Rook(char color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
        numOfMoves = 15;
    }

    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[numOfMoves];
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
