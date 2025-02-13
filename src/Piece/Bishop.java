package Piece;

import Collection.MoveList;
import Table.Table;
import Utilities.*;

import javax.swing.ImageIcon;


public class Bishop extends Piece {
    private boolean topRight;
    private boolean topLeft;
    private boolean bottomRight;
    private boolean bottomLeft;

    public Bishop(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.BISHOP;
    }

    public Bishop(Color color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
    }



    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        movements.clear();

        topRight = true;
        topLeft = true;
        bottomRight = true;
        bottomLeft = true;


        for(byte i = 1 ; i < size; ++i){
            aux(i,movements, size);
        }

        return (Coord[])movements.toArray();
    }



    private void aux(byte i, MoveList movements, byte size){
        if(topRight) {
            if (isInLimit(i, i, size)) {
                topRight = putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + i));
            }
        }

        if(topLeft) {
            if(isInLimit(i, -i, size)){
                topLeft = putMoves(movements, (byte) (coord.i + i), (byte) (coord.j - i));
            }
        }

        if(bottomRight) {
            if(isInLimit(-i, i, size)){
                bottomRight = putMoves(movements, (byte) (coord.i - i), (byte) (coord.j + i));
            }
        }

        if(bottomLeft) {
            if(isInLimit(-i, -i, size)){
                bottomLeft = putMoves(movements, (byte) (coord.i - i), (byte) (coord.j - i));
            }
        }

    }


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
