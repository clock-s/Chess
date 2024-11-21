package Piece.Queen;

import Piece.Bishop.Bishop;
import Piece.Rook.Rook;
import Piece.Piece;
import Piece.Coord;
import Table.Table;


import javax.swing.*;

public class Queen extends Piece {
    public Queen(char color, ImageIcon icon, Coord coord, Table table) {
        setType('Q');
        setColor(color);
        setCoord(coord);
        setTable(table);
        setIcon(icon);
    }

    @Override
    public Coord[] move() {
        Coord[] movementsBishop = new Bishop(this.color, this.coord, this.table).move();
        Coord[] movementsTower = new Rook(this.color, this.coord, this.table).move();
        this.index = 0;
        Coord[] movements = new Coord[30];

        setMovements(movements, movementsBishop);
        setMovements(movements, movementsTower);


        return movements;
    }

    private void setMovements(Coord[] movements, Coord[] moveBag) {
        for(Coord m : moveBag) {
            if(m == null){
                break;
            }
            movements[this.index++] = new Coord(m);
        }
    }

    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
