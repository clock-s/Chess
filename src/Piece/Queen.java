package Piece;

import Table.Table;
import Utilities.*;


import javax.swing.*;

public class Queen extends Piece {

    public Queen(byte id, Color color, ImageIcon icon, Coord coord, Table table) {
        super(id, color, icon, coord, table);
        this.category = Category.QUEEN;
    }

    @Override
    public Coord[] move() {
        movements.clear();

        movements.add(new Bishop(this.color, this.coord, this.table).move());
        movements.add(new Rook(this.color, this.coord, this.table).move());



        return (Coord[]) movements.toArray();
    }


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
