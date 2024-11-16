package Piece.Bishop;

import Piece.Piece;
import Piece.Rook.Rook;
import Table.Table;
import Piece.Coord;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
    private boolean topRight;
    private boolean topLeft;
    private boolean bottomRight;
    private boolean bottomLeft;

    public Bishop(char color, ImageIcon icon, Coord coord, Table table){
        setType('T');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    private byte index = 0;

    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[15];
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
            if (getCoord().i + i < size && getCoord().j + i < size) {
                topRight = putMoves(movements, (byte) (getCoord().i + i), (byte) (getCoord().j + i));
            }
        }

        if(topLeft) {
            if(getCoord().i + i < size && getCoord().j - i >= 0){
                topLeft = putMoves(movements, (byte) (getCoord().i + i), (byte) (getCoord().j - i));
            }
        }

        if(bottomRight) {
            if(getCoord().i - i >= 0 && getCoord().j + i < size){
                bottomRight = putMoves(movements, (byte) (getCoord().i - i), (byte) (getCoord().j + i));
            }
        }

        if(bottomLeft) {
            if(getCoord().i - i >= 0 && getCoord().j - i >= 0){
                bottomLeft = putMoves(movements, (byte) (getCoord().i - i), (byte) (getCoord().j - i));
            }
        }

    }

    private boolean putMoves(Coord[] movements, byte i, byte j){
        if(getTable()[i][j] == null){
            movements[this.index++] = new Coord(i,j);
        }else{
            if(getTable()[i][j].getColor() == getColor()){
                return false;
            }else{
                movements[this.index++] = new Coord(i,j);
                return false;
            }
        }

        return true;
    }

    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
