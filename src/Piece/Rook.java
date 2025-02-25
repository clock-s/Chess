package Piece;

import Table.Table;
import Utilities.Category;
import Utilities.Coord;
import Utilities.Color;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    private boolean left;
    private boolean right;
    private boolean top;
    private boolean bottom;


    public Rook(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.ROOK;
    }

    public Rook(Color color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
    }

    @Override
    public Coord[] move() {
        byte size = Table.LENGHT;
        this.movements.clear();

        top = true;
        bottom = true;
        left = true;
        right = true;


        for(byte i = (byte) (coord.i + 1) ; i < size; ++i){
            if(bottom){
               bottom = putMoves(movements, i, (byte) (coord.j));
            }
        }

        for(byte i = (byte) (coord.i - 1) ; i >= 0; --i){
            if(top){
                top = putMoves(movements, i, (byte) (coord.j));
            }
        }

        for(byte j = (byte) (coord.j + 1); j < size ; ++j){
            if(left){
                left = putMoves(movements, (byte) coord.i, j);
            }
        }

        for(byte j = (byte) (coord.j - 1) ; j >= 0 ; --j){

            if(right){
                right = putMoves(movements, (byte) coord.i, j);
            }
        }

        return (Coord[])this.movements.toArray();
    }


    @Override
    public Coord[] dangerZone() {
        dangerZone.clear();
        potentialDangerZone.clear();
        dangerZone.add(move());

        return (Coord[])dangerZone.toArray();
    }
}
