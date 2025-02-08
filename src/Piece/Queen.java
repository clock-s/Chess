package Piece;

import Table.Table;


import javax.swing.*;

public class Queen extends Piece {
    public Queen(byte id, char color, ImageIcon icon, Coord coord, Table table) {
        super(id, color, icon, coord, table);
        setType('Q');
        numOfMoves = 30;
    }

    @Override
    public Coord[] move() {
        Coord[] movementsBishop = new Bishop(this.color, this.coord, this.table).move();
        Coord[] movementsTower = new Rook(this.color, this.coord, this.table).move();
        this.index = 0;
        Coord[] movements = new Coord[numOfMoves];

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
