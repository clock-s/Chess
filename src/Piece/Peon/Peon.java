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

        if(color == 'w'){
            if(coord.i - 1 >= 0) {
                if (coord.j + 1 < size) {
                    dangerPlates[index++] = new Coord(coord.i - 1, coord.j + 1);
                }
                if (coord.j - 1 >= 0) {
                    dangerPlates[index++] = new Coord(coord.i - 1, coord.j - 1);
                }
            }
        }else if(color == 'b'){
            if(coord.i + 1 < size) {
                if (coord.j + 1 < size) {
                    dangerPlates[index++] = new Coord(coord.i + 1, coord.j + 1);
                }
                if (coord.j - 1 >= 0) {
                    dangerPlates[index++] = new Coord(coord.i + 1, coord.j - 1);
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


        if(color == 'w'){
            if(coord.i - 1 >= 0 && getTable()[coord.i - 1][coord.j] == null) {
                movements[index++] = new Coord(coord.i - 1, coord.j);

                if(getMoves() == 0 && getTable()[coord.i - 2][coord.j] == null){
                    movements[index++] = new Coord(coord.i -2,coord.j);
                }
            }
            

        }else if(color == 'b'){
            if(coord.i +1 < size && getTable()[coord.i + 1][coord.j] == null) {
                movements[index++] = new Coord(coord.i + 1, coord.j);

                if(getMoves() == 0 && getTable()[coord.i + 2][coord.j] == null){
                    movements[index++] = new Coord(coord.i + 2,coord.j);
                }
            }

        }

        Coord[] dangerZone = dangerZone();
        for(Coord d : dangerZone){
            if(d != null) {
                if(getTable()[d.i][d.j] != null) {
                    if (getTable()[d.i][d.j].getColor() != color) {
                        movements[index++] = d;
                    }
                }
            }
        }

        return movements;
    }
}
