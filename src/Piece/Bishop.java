package Piece;

import Table.Table;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
    private boolean topRight;
    private boolean topLeft;
    private boolean bottomRight;
    private boolean bottomLeft;

    public Bishop(byte id, char color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        setType('B');
        numOfMoves = 15;
    }

    public Bishop(char color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
        numOfMoves = 15;
    }



    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[numOfMoves];
        this.index = 0;
        topRight = true;
        topLeft = true;
        bottomRight = true;
        bottomLeft = true;


        for(byte i = 1 ; i < size; ++i){
            aux(i,movements, size);
        }

        return movements;
    }



    private void aux(byte i, Coord[] movements, byte size){
        if(topRight) {
            if (coord.i + i < size && coord.j + i < size) {
                topRight = putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + i));
            }
        }

        if(topLeft) {
            if(coord.i + i < size && coord.j - i >= 0){
                topLeft = putMoves(movements, (byte) (coord.i + i), (byte) (coord.j - i));
            }
        }

        if(bottomRight) {
            if(coord.i - i >= 0 && coord.j + i < size){
                bottomRight = putMoves(movements, (byte) (coord.i - i), (byte) (coord.j + i));
            }
        }

        if(bottomLeft) {
            if(coord.i - i >= 0 && coord.j - i >= 0){
                bottomLeft = putMoves(movements, (byte) (coord.i - i), (byte) (coord.j - i));
            }
        }

    }


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
