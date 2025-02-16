package Piece;

import Table.Table;
import Utilities.Category;
import Utilities.Coord;
import Utilities.Color;

import javax.swing.ImageIcon;

public class Rook extends Piece {
    private boolean left;
    private boolean right;
    private boolean top;
    private boolean bottom;

    private boolean pdzfLeft; //pdzf - PotentialDangerZoneFlag
    private boolean pdzfRight;
    private boolean pdzfTop;
    private boolean pdzfBottom;

    public Rook(byte id, Color color, ImageIcon icon, Coord coord, Table table){
        super(id, color, icon, coord, table);
        this.category = Category.ROOK;
    }

    public Rook(Color color, Coord coord, Table table){
        super((byte)0, color, null, coord, table);
    }

    @Override
    public Coord[] move() {
        byte size = Table.LENGHT;
        this.movements.clear();

        top = true;
        bottom = true;
        left = true;
        right = true;

        pdzfTop = false;
        pdzfBottom = false;
        pdzfLeft = false;
        pdzfRight = false;

        for(byte i = (byte) (coord.i + 1) ; i < size; ++i){
            if(bottom){
               bottom = putMoves(movements, i, (byte) (coord.j));
               if(!bottom) {
                   pdzfBottom = true;
                   continue;
               }
            }
            if(pdzfBottom){
                pdzfBottom = putMoves(potentialDangerZone, i, (byte) (coord.j));
                if(!pdzfBottom)  break;
            }
        }

        for(byte i = (byte) (coord.i - 1) ; i >= 0; --i){
            if(top){
                top = putMoves(movements, i, (byte) (coord.j));
                if(!top){
                    pdzfTop = true;
                    continue;
                }
            }
            if(pdzfTop){
                pdzfTop = putMoves(potentialDangerZone, i, (byte) (coord.j));
                if(!pdzfTop)  break;
            }

        }

        for(byte j = (byte) (coord.j + 1); j < size ; ++j){
            if(left){
                left = putMoves(movements, (byte) coord.i, j);
                if(!left){
                    pdzfLeft = true;
                    continue;
                }
            }
            if(pdzfLeft){
                pdzfLeft = putMoves(potentialDangerZone, (byte) coord.i, j);
                if(!pdzfLeft)  break;
            }
        }

        for(byte j = (byte) (coord.j - 1) ; j >= 0 ; --j){

            if(right){
                right = putMoves(movements, (byte) coord.i, j);
                if(!right){
                    pdzfRight = true;
                    continue;
                }
            }
            if(pdzfRight){
                pdzfRight = putMoves(potentialDangerZone, (byte) coord.i, j);
                if(!pdzfRight)  break;
            }

        }

        return (Coord[])this.movements.toArray();
    }


    @Override
    public Coord[] dangerZone() {
        dangerZone.clear();
        potentialDangerZone.clear();
        dangerZone.add(move());

        return (Coord[])dangerZone.toArray();
    }
}
