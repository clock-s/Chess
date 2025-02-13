package Piece;

import Table.Table;
import Utilities.Category;
import Utilities.Coord;
import Utilities.Color;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    public Rook(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.ROOK;
    }

    public Rook(Color color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
    }

    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        this.movements.clear();

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

        return (Coord[])this.movements.toArray();
    }


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
