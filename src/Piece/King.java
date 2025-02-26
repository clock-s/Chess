package Piece;

import Collection.MoveList;
import Player.Player;
import Table.Table;
import Utilities.*;

import javax.swing.*;

public class King extends Piece {
    private Player master;
    protected MoveList specialMove = new MoveList();
    public boolean littleRock = this.LitlleRockPossible();
    public boolean bigRock = this.BigRockPossible();

    public Coord[] getSpecialMovements(){return (Coord[])specialMove.toArray();}

    public King(byte id, Color color, ImageIcon icon, Coord coord, Table table, Player master) {
        super(id, color, icon, coord, table);
        this.category = Category.KING;
        this.master = master;
    }


    @Override
    public Coord[] move() {
        movements.clear();
        for(byte i = -1 ; i <= 1 ; ++i){
            for (byte j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (isInLimit(i, j) && seeOtherTable(coord.i + i, coord.j + j).getPlateState() != PlateState.DANGER) {
                    putMoves(movements, (byte) (coord.i + i), (byte) (coord.j + j));
                }
            }
        }
        if(this.LitlleRockPossible()){
            putMoves(movements, (byte) (coord.i + 0), (byte) (coord.j + 2));
        }
        if(this.BigRockPossible()){
            putMoves(movements, (byte) (coord.i + 0), (byte) (coord.j - 2));
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


    public boolean LitlleRockPossible(){
        boolean flag = true;

// verifica se o rei ta em check
        if(isCheck()){
            flag = false;
        }
//verifica se o rei ja moveu, se a peça é uma torre e se essa torre tem movimentos == 0;
        if(this.getMoves() != 0 || seeOtherTable(coord.i, 7).getPiece().getCategory() != category.ROOK || seeOtherTable(coord.i, 7).getPiece().getMoves() != 0 ){
            flag = false;
        }
//verifica as duas casas ao lado DIREITO estão atacadas
        if(seeOtherTable(coord.i , coord.j + 1).getPlateState() == PlateState.DANGER || seeOtherTable(coord.i, coord.j + 2).getPlateState() == PlateState.DANGER || seeOtherTable(coord.i , coord.j + 1).getPlateState() == PlateState.BOTH || seeOtherTable(coord.i, coord.j + 2).getPlateState() == PlateState.BOTH){
            flag = false;
        }
// verifica se a segunda casa ao lado DIREITO (j+2) está vazia
        if(seeOtherTable(coord.i, coord.j + 2).getPiece() != null ){
            flag = false;
        }
//verifica se a primeira casa ao lado Direito (j+1) está vazia
        if(seeOtherTable(coord.i , coord.j + 1).getPiece() != null ){
            flag = false;
        }
        return flag;
    }



    //verifica se ta podendo fazer o roque grande
    public boolean BigRockPossible(){
        boolean flag = true;
//verifica se o rei ta em check
        if(isCheck()){
            flag = false;
        }
//verifica se o rei ja moveu ou se a torre ja moveu
        if(this.getMoves() != 0 ||seeOtherTable(coord.i, 0).getPiece().getCategory() != category.ROOK || seeOtherTable(coord.i, 0).getPiece().getMoves() != 0  ){
            flag =  false;
        }
//verifica as duas casas ao lado ESQUERDO estão atacadas
        if(seeOtherTable(coord.i , coord.j - 1).getPlateState() == PlateState.DANGER || seeOtherTable(coord.i, coord.j - 2).getPlateState() == PlateState.DANGER || seeOtherTable(coord.i , coord.j - 1).getPlateState() == PlateState.BOTH || seeOtherTable(coord.i, coord.j - 2).getPlateState() == PlateState.BOTH){
            flag = false;
        }
//verifica se a PRIMEIRA casa ao lado ESQUERDO está vazia
        if(seeOtherTable(coord.i, coord.j - 1).getPiece() != null){
            flag = false;
        }
//verifica se a SEGUNDA casa ao lado ESQUERDO está vazia
        if(seeOtherTable(coord.i, coord.j - 2).getPiece() != null){
            flag = false;
        }
//verifica se a TERCEIRA casa ao lado ESQUERDO está vazia
        if(seeOtherTable(coord.i, coord.j - 3).getPiece() != null){
            flag = false;
        }
        return flag;
    }

    public Player getMaster() {
        return master;
    }
}