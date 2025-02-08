package Piece;
import Table.Table;

import javax.swing.*;


public class Peon extends Piece {
    public Peon(byte id, char color, ImageIcon icon, Coord coord, Table table) {
        super(id, color, icon, coord, table);
        setType('P');
        numOfMoves = 2;
    }

    @Override
    public Coord[] dangerZone() {
        byte size = (byte) getTable().length;
        Coord[] dangerPlates = new Coord[numOfMoves];
        this.index = 0;

        if(color == 'w'){
            if(coord.i - 1 >= 0) {
                if (coord.j + 1 < size) {
                    dangerPlates[this.index++] = new Coord(coord.i - 1, coord.j + 1);
                }
                if (coord.j - 1 >= 0) {
                    dangerPlates[this.index++] = new Coord(coord.i - 1, coord.j - 1);
                }
            }
        }else if(color == 'b'){
            if(coord.i + 1 < size) {
                if (coord.j + 1 < size) {
                    dangerPlates[this.index++] = new Coord(coord.i + 1, coord.j + 1);
                }
                if (coord.j - 1 >= 0) {
                    dangerPlates[this.index++] = new Coord(coord.i + 1, coord.j - 1);
                }
            }
        }

        return dangerPlates;
    }

    @Override
    public Coord[] move(){
        byte size = (byte)getTable().length;
        Coord[] movements = new Coord[numOfMoves + 2];
        this.index = 0;


        if(color == 'w'){
            if(coord.i - 1 >= 0 && getTable()[coord.i - 1][coord.j] == null) {
                movements[this.index++] = new Coord(coord.i - 1, coord.j);

                if(getMoves() == 0 && getTable()[coord.i - 2][coord.j] == null){
                    movements[this.index++] = new Coord(coord.i -2,coord.j);
                }
            }
            

        }else if(color == 'b'){
            if(coord.i +1 < size && getTable()[coord.i + 1][coord.j] == null) {
                movements[this.index++] = new Coord(coord.i + 1, coord.j);

                if(getMoves() == 0 && getTable()[coord.i + 2][coord.j] == null){
                    movements[this.index++] = new Coord(coord.i + 2,coord.j);
                }
            }

        }

        Coord[] dangerZone = dangerZone();
        for(Coord d : dangerZone){
            if(d != null) {
                if(getTable()[d.i][d.j] != null) {
                    if (getTable()[d.i][d.j].getColor() != color) {
                        movements[this.index++] = d;
                    }
                }
            }
        }

        return movements;
    }
}
