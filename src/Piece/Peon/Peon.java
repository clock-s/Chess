package Piece.Peon;
import Piece.Piece;
import Piece.Coord;
import Table.Table;

import javax.swing.*;


public class Peon extends Piece {
    public Peon(char color, ImageIcon icon, Coord coord, Table table) {
        setType('P');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    @Override
    public Coord[] dangerZone() {
        byte size = (byte) getTable().length;
        Coord[] dangerPlates = new Coord[2];
        byte index = 0;

        if(getColor() == 'w'){
            if(getCoord().i - 1 >= 0) {
                if (getCoord().j + 1 < size) {
                    dangerPlates[index++] = new Coord(getCoord().i - 1, getCoord().j + 1);
                }
                if (getCoord().j - 1 >= 0) {
                    dangerPlates[index++] = new Coord(getCoord().i - 1, getCoord().j - 1);
                }
            }
        }else if(getColor() == 'b'){
            if(getCoord().i + 1 < size) {
                if (getCoord().j + 1 < size) {
                    dangerPlates[index++] = new Coord(getCoord().i + 1, getCoord().j + 1);
                }
                if (getCoord().j - 1 >= 0) {
                    dangerPlates[index++] = new Coord(getCoord().i + 1, getCoord().j - 1);
                }
            }
        }

        return dangerPlates;
    }

    @Override
    public Coord[] move(){
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[4];
        byte index = 0;


        if(getColor() == 'w'){
            if(getCoord().i - 1 >= 0 && getTable()[getCoord().i - 1][getCoord().j] == null) {
                movements[index++] = new Coord(getCoord().i - 1, getCoord().j);

                if(getMoves() == 0 && getTable()[getCoord().i - 2][getCoord().j] == null){
                    movements[index++] = new Coord(getCoord().i -2,getCoord().j);
                }
            }
            

        }else if(getColor() == 'b'){
            if(getCoord().i +1 < size && getTable()[getCoord().i + 1][getCoord().j] == null) {
                movements[index++] = new Coord(getCoord().i + 1, getCoord().j);

                if(getMoves() == 0 && getTable()[getCoord().i + 2][getCoord().j] == null){
                    movements[index++] = new Coord(getCoord().i + 2,getCoord().j);
                }
            }

        }

        Coord[] dangerZone = dangerZone();
        for(Coord d : dangerZone){
            if(d != null) {
                if(getTable()[d.i][d.j] != null) {
                    if (getTable()[d.i][d.j].getColor() != getColor()) {
                        movements[index++] = d;
                    }
                }
            }
        }

        return movements;
    }
}
