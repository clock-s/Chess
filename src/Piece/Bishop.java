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
    private boolean potentialDangerStopFlag = false;

    public Bishop(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.BISHOP;
    }

    public Bishop(Color color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
    }



    @Override
    public Coord[] move() {
        byte size = Table.LENGHT;
        movements.clear();
        potentialDangerZone.clear();


        topRight = true;
        topLeft = true;
        bottomRight = true;
        bottomLeft = true;


        for(byte i = 1 ; i < size; ++i){
            aux(i);
        }

        return (Coord[])movements.toArray();
    }



    private void aux(byte i){

        if(isInLimit(i,i)) {
            if (bottomRight) {
                bottomRight = putMoves(this.movements, (byte) (coord.i + i), (byte) (coord.j + i));
            } else {
                putMoves(this.potentialDangerZone, (byte) (coord.i + i), (byte) (coord.j + i));
            }
        }

        if(isInLimit(i,-i)) {
            if (bottomLeft) {
                bottomLeft = putMoves(this.movements, (byte) (coord.i + i), (byte) (coord.j - i));
            } else {
                putMoves(this.potentialDangerZone, (byte) (coord.i + i), (byte) (coord.j - i));
            }
        }

        if(isInLimit(-i,i)) {
            if(topRight) {
                topRight = putMoves(this.movements, (byte) (coord.i - i), (byte) (coord.j + i));
            }else{
                putMoves(this.potentialDangerZone, (byte) (coord.i - i), (byte) (coord.j + i));
            }
        }


        if(isInLimit(-i,-i)) {
            if(topLeft) {
                topLeft = putMoves(this.movements, (byte) (coord.i - i), (byte) (coord.j - i));
            }else{
                putMoves(this.potentialDangerZone, (byte) (coord.i - i), (byte) (coord.j - i));
            }
        }


    }


    @Override
    public Coord[] dangerZone() {
        dangerZone.clear();
        dangerZone.add(move());
        return (Coord[])dangerZone.toArray();
    }
}
