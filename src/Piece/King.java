package Piece;

import Player.Player;
import Table.Table;
import Utilities.*;

import javax.swing.*;

public class King extends Piece {
    private Player master;

    public King(byte id, Color color, ImageIcon icon, Coord coord, Table table, Player master) {
        super(id, color, icon, coord, table);
        this.category = Category.KING;
        this.master = master;
    }



    @Override
    public Coord[] move() {
        movements.clear();


        for(byte i = -1 ; i <= 1 ; ++i){
            for(byte j = -1; j <= 1; ++j){
                if(i == 0 && j == 0){
                    continue;
                }
                if(isInLimit(i,j) && seeOtherTable(coord.i + i, coord.j + j).getPlateSate() != PlateState.DANGER){
                    putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + j));
                }
            }
        }


        /*
        if(master.isRound()){
            //checkLimitation(movements);
        }
        */


        return (Coord[])movements.toArray();
    }



    @Override
    public Coord[] dangerZone() {
        dangerZone.clear();
        potentialDangerZone.clear();
        dangerZone.add(move());
        return (Coord[])dangerZone.toArray();
    }
}
