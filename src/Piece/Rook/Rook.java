package Piece.Rook;

import Piece.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.ImageIcon;

public class Rook extends Piece {
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
        byte index = 0;

        for(int i = getCoord().i; i < size; ++i){
            if(getTable()[i][getCoord().j].getColor() == getColor()){
                break;
            } else{
                movements[index++] = new Coord(i,getCoord().j);
            }
        }

        for(int i = getCoord().i; i >= 0; --i){
            if(getTable()[i][getCoord().j].getColor() == getColor()){
                break;
            } else{
                movements[index++] = new Coord(i,getCoord().j);
            }
        }

        for(int j = getCoord().j; j < size ; ++j){
            if(getTable()[getCoord().i][j].getColor() == getColor()){
                break;
            } else{
                movements[index++] = new Coord(getCoord().i,j);
            }
        }

        for(int j = getCoord().j; j >= 0 ; --j){
            if(getTable()[getCoord().i][j].getColor() == getColor()){
                break;
            } else{
                movements[index++] = new Coord(getCoord().i,j);
            }
        }

        return movements;
    }

    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
