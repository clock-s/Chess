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
        return (Coord[])dangerZone.toArray();
    }

    //função que verifica se o rei pode sair do check
    public boolean reiPodeMover(){

        if(this.move().length == 0) return false;

        return true;
    }

    public boolean isCheck(){
        if(seeOtherTable(coord.i, coord.j).getPlateSate() == PlateState.DANGER) return true;

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



// ATENÇÃO O CÓDIGO ABAIXO AINDA PRECISA FAZER ALTERAÇÕES ADEQUADAS PARA KING E ROOK E SUAS ADVERSIDADES
// É PRECISO TAMBÉM DEBATER SOBRE ONDE AS FUNÇÕES SERÃO COLOCADAS (PLAYER, KING OU TABLE)
/*

    //função q verifica se ta podendo fazer o roque pequeno
    public boolean LitlleRockPossible(){
        boolean flag = true;

// verifica se o rei ta em check
        if(Player.inCheck){
            flag = false;
        }
//verifica se o rei ja moveu ou se a torre ja moveu
        else if(king.moves != 0 || Rook.moves != 0){
            flag = false;
        }
//verifica as duas casas ao lado DIREITO estão atacadas
        else if(seeOtherPlates(coord.i , coord.j + 1).getPlateState == PlateState.DANGER || seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateState == PlateState.DANGER){
            flag = false;
        }
// verifica se a segunda casa ao lado DIREITO (j+2) está vazia
        else if(seeOtherPlates(coord.i, coord.j + 2).getPlateSate() != null ){
            flag = false;
        }
//verifica se a primeira casa ao lado Direito (j+1) está vazia
        else if(seeOtherPlates(coord.i , coord.j + 1).getPlateSate() != null ){
            flag = false;
        }
// se passar por todas as verificações, retorna true
        else{
            flag = true;
        }

        return flag;
    }



    //verifica se ta podendo fazer o roque grande
    public boolean BigRockPossible(){

        boolean flag = true;
//verifica se o rei ta em check
        if(inCheck()){
            flag = false;
        }

//verifica se o rei ja moveu ou se a torre ja moveu
        else if(moves != 0 || Rook.moves != 0){
            flag =  false;
        }
//verifica as duas casas ao lado ESQUERDO estão atacadas
        else if(seeOtherPlates(coord.i , coord.j - 1).getPlateState == PlateState.DANGER || seeOtherPlates(coord.i, coord.j - 2).getPlateState == PlateState.DANGER){
            flag = false;
        }
//verifica se a PRIMEIRA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(coord.i, coord.j + 2).getPlateSate() != null){
            flag = false;
        }
//verifica se a SEGUNDA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(coord.i, coord.j + 2).getPlateSate() != null){
            flag = false;
        }
// se passar por todas as verificações, retorna true
        else{
            flag = true;
        }

        return flag;
    }

    // função fazer o roque pequeno
    public void makeLitlleRock(){
// se for possível fazer o roque e o jogador clicou duas casas a direita
        if(litlleRockPossible() &&  ){
            table.newPiecePosition(getCoord(), new Coord(coord.i, coord.j + 2));
            table.newPiecePosition(Rook.getCoord(), new Coord(Rook.coord.i, coord.j - 2));
        }
    }
    // função fazer o roque grande
    public void makeBigRock(){
        // se for possível fazer o roque e o jogador clicou duas a esquerda
        if(BigRockPossible() && ){
            table.newPiecePosition(getCoord(), new Coord(coord.i, coord.j - 2));
            table.newPiecePosition(Rook.getCoord(), new Coord(Rook.coord.i, coord.j + 3));
        }
    }

    //lógica para CHECKMATE, versão não definitiva



    //função que verifica se é possível comer a peça que está atacando o rei com check
    public boolean comerAtacante(King king, Piece atacante){
        //verifica se algum vetor de ataque chega no atacante
        if(){
            return true;
        }
        return false;
    }
    //função que verifica se é possível tampar o check


    public boolean isCheckmate(){
        if(isCheck()){
            if(PodetamparCheck(null, null) == false && comerAtacante(null, null) == false && !reiPodeMover()){
                return true;
            }
            else{
                return false;
            }
        }
        i

    }



 */

}