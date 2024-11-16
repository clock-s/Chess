package Piece.Rook;

import Piece.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    private byte index;

    public Rook(char color, ImageIcon icon, Coord coord, Table table){
        setType('T');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    @Override
    public Coord[] move() {
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[15];
        this.index = 0;

        for(byte i = (byte) (getCoord().i + 1) ; i < size; ++i){
            if(!putMoves(movements, i, (byte) (getCoord().j))){
                break;
            }
        }

        for(byte i = (byte) (getCoord().i - 1) ; i >= 0; --i){
            if(!putMoves(movements, i, (byte) (getCoord().j))){
                break;
            }
        }

        for(byte j = (byte) (getCoord().j + 1); j < size ; ++j){
            if(!putMoves(movements, (byte) getCoord().i, j)){
                break;
            }
        }

        for(byte j = (byte) (getCoord().j - 1) ; j >= 0 ; --j){
            if(!putMoves(movements, (byte) getCoord().i, j)){
                break;
            }
        }

        return movements;
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
