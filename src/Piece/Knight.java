package Piece;

import Table.Table;
import Utilities.*;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.KNIGHT;
    }

    public Coord[] move(){
        byte size = Table.LENGHT;
        this.movements.clear();

        if(coord.i - 2 >= 0){
            if(coord.j - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 2), (byte) (coord.j - 1));
            }
            if(coord.j + 1 < size){
                putMoves(movements,(byte) (coord.i - 2), (byte) (coord.j + 1));
            }
        }
        if(coord.i + 2 < size){
            if(coord.j - 1 >= 0){
                putMoves(movements,(byte) (coord.i + 2), (byte) (coord.j - 1));
            }
            if(coord.j + 1 < size){
                putMoves(movements,(byte) (coord.i + 2), (byte) (coord.j + 1));
            }
        }

        if(coord.j - 2 >= 0){
            if(coord.i - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 1), (byte) (coord.j - 2));
            }
            if(coord.i + 1 < size){
                putMoves(movements,(byte) (coord.i + 1), (byte) (coord.j - 2));
            }
        }
        if(coord.j + 2 < size){
            if(coord.i - 1 >= 0){
                putMoves(movements,(byte) (coord.i - 1), (byte) (coord.j + 2));
            }
            if(coord.i + 1 < size){
                putMoves(movements,(byte) (coord.i + 1), (byte) (coord.j + 2));
            }
        }

        return (Coord[]) movements.toArray();
    }
    public Coord[] dangerZone(){
        dangerZone.clear();
        dangerZone.add(move());
        return (Coord[])dangerZone.toArray();
    }
}
