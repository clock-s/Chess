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


    //função q verifica se ta podendo fazer o roque pequeno
    public boolean LitlleRockPossible(){
        // veriffica se o rei ta em check 
        if(Player.inCheck){
            return false;
        }
        //verifica se o rei ja moveu ou se a torre ja moveu
        else if(king.jaMoveu() || Rook.jaMoveu()){
            return false;
        }
        //verifica as duas casas ao lado DIREITO estão atacadas
        else if(seeOtherPlates(king.coord.i , king.coord.j + 1).getPlateState == PlateState.DANGER || seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateState == PlateState.DANGER){
            return false;
        }
        // verifica se a segunda casa ao lado DIREITO (j+2) está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null ){
            return false;
        }
        //verifica se a primeira casa ao lado Direito (j+1) está vazia
        else if(seeOtherPlates(king.coord.i , king.coord.j + 1).getPlateSate() != null ){
            return false;
        }
        // se passar por todas as verificações, retorna true
        else if(){
            return true;
        }
    }

    //verifica se ta podendo fazer o roque grande
    public boolean BigRockPossible(){
        //verifica se o rei ta em check
        if(Player.inCheck){
            return false;
        }

         //verifica se o rei ja moveu ou se a torre ja moveu
        else if(King.jaMoveu() || Rook.jaMoveu()){
            return false;
        }
        //verifica as duas casas ao lado ESQUERDO estão atacadas
        else if(seeOtherPlates(king.coord.i , king.coord.j - 1).getPlateState == PlateState.DANGER || seeOtherPlates(king.coord.i, king.coord.j - 2).getPlateState == PlateState.DANGER){

        }
        //verifica se a PRIMEIRA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null){

        }
        //verifica se a SEGUNDA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null){

        }
        // se passar por todas as verificações, retorna true
        else{
            return true;
        }
    }
}
