package Piece;
import Table.Table;
import Utilities.*;

import javax.swing.*;


public class Pawn extends Piece {
    public Pawn(byte id, Color color, ImageIcon icon, Coord coord, Table table) {
        super(id, color, icon, coord, table);
        this.category = Category.PAWN;
    }

    @Override
    public Coord[] dangerZone() {
        byte size = Table.LENGHT;
        this.dangerZone.clear();
        this.potentialDangerZone.clear();

        if(color == Color.WHITE){
            if(coord.i - 1 >= 0) {
                if (coord.j + 1 < size) {
                    this.dangerZone.add(new Coord(coord.i - 1, coord.j + 1));
                }
                if (coord.j - 1 >= 0) {
                    this.dangerZone.add(new Coord(coord.i - 1, coord.j - 1));
                }
            }
        }else if(color == Color.BLACK){
            if(coord.i + 1 < size) {
                if (coord.j + 1 < size) {
                    this.dangerZone.add(new Coord(coord.i + 1, coord.j + 1));
                }
                if (coord.j - 1 >= 0) {
                    this.dangerZone.add(new Coord(coord.i + 1, coord.j - 1));
                }
            }
        }

        for(Coord d : (Coord[])this.dangerZone.toArray()){
            if(d != null) {
                if(seeOtherTable(d.i,d.j).getPiece() == null || seeOtherTable(d.i,d.j).getPiece().getColor() == color) {
                    this.dangerZone.remove(d);
                    this.potentialDangerZone.add(d);
                }
            }
        }

        return (Coord[])this.dangerZone.toArray();
    }

    @Override
    public Coord[] move(){
        byte size = Table.LENGHT;
        this.movements.clear();


        if(color == Color.WHITE){
            if(coord.i - 1 >= 0 && seeOtherTable(coord.i - 1, coord.j).getPiece() == null) {
                this.movements.add(new Coord(coord.i - 1, coord.j));

                if(getMoves() == 0 && seeOtherTable(coord.i - 2, coord.j).getPiece() == null){
                    this.movements.add(new Coord(coord.i - 2, coord.j));
                }
            }
            

        }else if(color == Color.BLACK){
            if(coord.i +1 < size && seeOtherTable(coord.i + 1, coord.j).getPiece() == null) {
                this.movements.add(new Coord(coord.i + 1, coord.j));

                if(getMoves() == 0 && seeOtherTable(coord.i + 2, coord.j).getPiece() == null){
                    this.movements.add(new Coord(coord.i + 2, coord.j));
                }
            }

        }


        this.movements.add(dangerZone());


        return (Coord[])this.movements.toArray();
    }
}
