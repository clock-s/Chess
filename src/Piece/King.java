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
// ATENÇÃO O CÓDIGO ABAIXO AINDA PRECISA FAZER ALTERAÇÕES ADEQUADAS PARA KING E ROOK E SUAS ADVERSIDADES
// É PRECISO TAMBÉM DEBATER SOBRE ONDE AS FUNÇÕES SERÃO COLOCADAS (PLAYER, KING OU TABLE)


    //função q verifica se ta podendo fazer o roque pequeno
    public boolean LitlleRockPossible(){
        boolean flag = true;

// verifica se o rei ta em check
        if(Player.inCheck){
            flag = false;
        }
//verifica se o rei ja moveu ou se a torre ja moveu
        else if(king.jaMoveu() || Rook.jaMoveu()){
            flag = false;
        }
//verifica as duas casas ao lado DIREITO estão atacadas
        else if(seeOtherPlates(king.coord.i , king.coord.j + 1).getPlateState == PlateState.DANGER || seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateState == PlateState.DANGER){
            flag = false;
        }
// verifica se a segunda casa ao lado DIREITO (j+2) está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null ){
            flag = false;
        }
//verifica se a primeira casa ao lado Direito (j+1) está vazia
        else if(seeOtherPlates(king.coord.i , king.coord.j + 1).getPlateSate() != null ){
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
        if(Player.inCheck){
            flag = false;
        }

//verifica se o rei ja moveu ou se a torre ja moveu
        else if(King.moves != 0 || Rook.moves != 0){
            flag =  false;
        }
//verifica as duas casas ao lado ESQUERDO estão atacadas
        else if(seeOtherPlates(king.coord.i , king.coord.j - 1).getPlateState == PlateState.DANGER || seeOtherPlates(king.coord.i, king.coord.j - 2).getPlateState == PlateState.DANGER){
            flag = false;
        }
//verifica se a PRIMEIRA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null){
            flag = false;
        }
//verifica se a SEGUNDA casa ao lado ESQUERDO está vazia
        else if(seeOtherPlates(king.coord.i, king.coord.j + 2).getPlateSate() != null){
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
        if(litlleRockPossible() && ){
            table.newPiecePosition(King.getCoord(), new Coord(king.coord.i, king.coord.j + 2));
            table.newPiecePosition(Rook.getCoord(), new Coord(Rook.coord.i, king.coord.j - 2));
        }
    }
    // função fazer o roque grande
    public void makeBigRock(){
        // se for possível fazer o roque e o jogador clicou duas a esquerda
        if(BigRockPossible() && ){
            table.newPiecePosition(King.getCoord(), new Coord(king.coord.i, king.coord.j - 2));
            table.newPiecePosition(Rook.getCoord(), new Coord(Rook.coord.i, king.coord.j + 3));
        }
    }

    //lógica para CHECKMATE, versão não definitiva

    //função que verifica se o rei pode sair do check
    public boolean reiPodeMover(King king){
        //verifica as todas casas ao redor do rei estão sob ataque
        if(){
            return true
        }
        //verifica se  casas ao redor do rei estão ocupadas por peças da mesma cor
        if(){
            return true;
        }
        return false;
    }

    //função que verifica se é possível comer a peça que está atacando o rei com check
    public boolean comerAtacante(King king, Piece atacante){
        //verifica se algum vetor de ataque chega no atacante
        if(){
            return true;
        }
        return false;
    }
    //função que verifica se é possível tampar o check
    public boolean tamparCheck(King king, Piece atacante){
        //verifica primeiramente se é um bispo, torre ou rainha
        if(atacante == category.BISHOP || atacante == category.QUEEN || atacante == category.ROOK){
            //caso seja: verifica se existem casas entre o rei e o atacante
            if(){
                //caso existam: verifica se alguma peça pode se movimentar para essa casa
                if(){
                    return true;
                }
            }
        }

        return false;

    }

    public boolean isCheckmate(){
        if(tamparCheck(null, null) == false && comerAtacante(null, null) == false && reiPodeMover(null) == false){
            return true;
        }
        else{
            return false;
        }

    }

}