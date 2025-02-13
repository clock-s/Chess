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
        byte size = (byte) getTable().length;
        movements.clear();

        for(byte i = -1 ; i <= 1 ; ++i){
            for(byte j = -1; j <= 1; ++j){
                if(i == 0 && j == 0){
                    continue;
                }
                if(isInLimit(i,j,size)){
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

    /*
    private void checkLimitation(Coord[] movements) {
        Coord[][] enemyZone = master.getEnemysDangerZone();;

        for(byte i = 0 ; i < movements.length ; ++i){
            if(movements[i] != null){
                for(byte j = 0 ; j < enemyZone.length ; ++j){
                    if(enemyZone[j] != null){
                        for(byte k = 0 ; k < enemyZone[j].length ; ++k){
                            if(enemyZone[j][k] != null){
                                if(movements[i].isEquals(enemyZone[j][k])){
                                    movements[i] = null;
                                }
                            }
                            if(movements[i] == null){
                                break;
                            }
                        }
                        if(movements[i] == null){
                            break;
                        }
                    }
                    if(movements[i] == null){
                        break;
                    }
                }
            }
        }

    }
    */


    @Override
    public Coord[] dangerZone() {
        return move();
    }
}
