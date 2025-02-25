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

                boolean dontPut = false;

                if(isInLimit(i,j)){
                    if(seeOtherTable(coord.i + i, coord.j + j).getPlateState() == PlateState.POTENTIAL){
                        for(Piece threater : seeOtherTable(coord.i + i, coord.j + j).getTheters()){
                            if(threater instanceof Pawn || threater instanceof Knight){
                                dontPut = true;
                                break;
                            }
                        }
                    }

                    if(seeOtherTable(coord.i + i, coord.j + j).getPlateState() == PlateState.DANGER) dontPut = true;
                    if(seeOtherTable(coord.i + i, coord.j + j).getPlateState() == PlateState.BOTH) dontPut = true;

                    if(!dontPut){
                        putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + j));
                    }
                }


            }
        }

        if(isCheck()){
            Coord remover = removeIllegalMoves();

            for(Coord r : (Coord[]) movements.toArray()){
                if(r.isEquals(remover)){
                    movements.remove(r);
                }
            }

        }


        return (Coord[])movements.toArray();
    }



    @Override
    public Coord[] dangerZone() {
        dangerZone.clear();
        potentialDangerZone.clear();
        return (Coord[])dangerZone.toArray();
    }

    //função que verifica se o rei pode sair do check
    public boolean reiPodeMover(){

        if(this.move().length == 0) return false;

        return true;
    }

    public boolean isCheck(){
        if(seeOtherTable(coord.i, coord.j).getPlateState() == PlateState.DANGER) return true;

        return false;
    }

    public Threater blockPossible(){
        //verifica primeiramente se é um bispo, torre ou rainha

        for(Piece threater : seeOtherTable(coord.i, coord.j).getTheters()){
            if(threater instanceof Bishop || threater instanceof Queen || threater instanceof Rook){
                int y = threater.coord.i - coord.i;
                int x = threater.coord.j - coord.j;

                if(x == 1 || x == -1 || y == 1 || y == -1){
                    return new Threater(false, threater);
                }
            }else if(threater instanceof Pawn || threater instanceof Knight){
                return new Threater(false, threater);
            }
            return new Threater(true, threater);
        }


        return null;
    }

    private Coord removeBackBishopAttack(Piece threater, Coord remover){
        int bi = threater.getCoord().i;
        int bj = threater.getCoord().j;



        //bispo superior esquerda
        if(this.coord.j > bj && this.coord.i > bi){
            remover = new Coord(this.coord.i + 1 , this.coord.j + 1);
        }
        //bispo inferior esquerda
        else if( this.coord.j > bj && this.coord.i < bi ){
            remover = new Coord(this.coord.i - 1 , this.coord.j + 1);
        }
        //bispo superior direita
        else if( this.coord.j < bj && this.coord.i > bi ){
            remover = new Coord(this.coord.i + 1 , this.coord.j - 1);
        }
        //bispo inferior direita
        else if( this.coord.j < bj && this.coord.i < bi ){
            remover = new Coord(this.coord.i - 1 , this.coord.j - 1);
        }

        return remover;
    }

    public Coord removeBackRookAttack(Piece threater, Coord remover){
        int y = coord.i - threater.getCoord().i;
        int x = coord.j - threater.getCoord().j;

        if(x == 0){
            //Torre em cima
            if(y > 0){
                remover = new Coord(this.coord.i + 1 , this.coord.j);
            }
            //Torre em baixo
            else if( y < 0){
                remover = new Coord(this.coord.i - 1 , this.coord.j);
            }

        }else if(y == 0){
            //Torre na esquerda
            if(x > 0){
                remover = new Coord(this.coord.i , this.coord.j + 1);
            }
            //Torre na direita
            else if( x < 0){
                remover = new Coord(this.coord.i, this.coord.j - 1);
            }

        }else{
            return new Coord();
        }

        return remover;
    }


    private Coord removeIllegalMoves(){
        Piece threater = seeOtherTable(coord.i, coord.j).getTheters()[0];
        Coord remover = new Coord();

        if(threater instanceof Bishop ) {
            remover = removeBackBishopAttack(threater, remover);
        }else if(threater instanceof Rook ) {
            remover = removeBackRookAttack(threater, remover);
        }else if(threater instanceof Queen ) {
            remover = removeBackRookAttack(threater, remover);

            if(remover.isEquals(new Coord())){
                remover = removeBackBishopAttack(threater, remover);
            }

        }


        return remover;
    }


}