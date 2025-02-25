package Player;

import Collection.PieceList;
import GUI.GUI;
import Storie.Arquivo;
import Table.Table;
import Utilities.Category;
import Utilities.Color;
import Utilities.Coord;
import Piece.Piece;
import Piece.King;
import Utilities.PlateState;
import Utilities.Threater;
import Collection.MoveList;
import Piece.Rook;
import Piece.Bishop;
import Piece.Queen;

public class Player{
    final private Color color;
    final private Table table;
    private boolean inCheck = false;
    final private GUI gui;
    private King king;
    private static Category choice = null;
    private PieceList pieces = new PieceList();
    private boolean mate = false;
    private boolean staleMate = false;
    private boolean noMoves = true;




    public void putPieceInList(Piece piece) {
        this.pieces.add(piece);
    }
    public void removePieceFromList(Piece piece) {
        this.pieces.remove(piece);
    }

    public void setStatusPlates(){
        for(Piece p : (Piece[]) pieces.toArray()){
            Coord[] danger = p.dangerZone();
            Coord[] potential = p.getPotentialDangerZone();

            for(Coord d : danger){
                table.getMap()[d.i][d.j].setPlateState(PlateState.DANGER);
                table.getMap()[d.i][d.j].setThreaters(p);
            }


            for(Coord pot : potential){
                table.getMap()[pot.i][pot.j].setPlateState(PlateState.POTENTIAL);
                table.getMap()[pot.i][pot.j].setThreaters(p);
            }

        }
    }

    public static void setChoice(Category c){choice = c;}
    public static Category getChoice(){return choice;}

    public Player(Color color, Table table, GUI gui) {
        this.color = color;
        this.table = table;
        this.gui = gui;
    }

    public Color getColor() {
        return color;
    }


    private void setPlatesThatPlayerCanUse() {
        Coord coord = new Coord();

        for(int i = 0; i < table.LENGHT; ++i){
            for(int j = 0; j < table.LENGHT; ++j){
                coord.setCoord(i,j);
                gui.getPlate(coord).setIsEnable(false);
            }
        }

        for(Piece p : (Piece[])pieces.toArray()){
            gui.getPlate(p.getCoord()).setIsEnable(true);
            for(Coord c : p.getMovements()){
                gui.getPlate(c).setIsEnable(true);
            }
        }


    }

    public void setKing(Piece king){
        this.king = (King) king;
    }








    public void round(){
        gui.resetCoord();
        setPlatesThatPlayerCanUse();

        noMoves = true;

        for(Piece p : (Piece[])pieces.toArray()){
            p.move();
        }

        inCheck = king.isCheck();

        if(inCheck){
            boolean kingCanMove = false;
            System.out.println("Player " + color.toString() + " in check");

            if(king.reiPodeMover()){
                kingCanMove = true;
            }

            Threater threater = king.blockPossible();

            if(threater.getIsThreater() == false){
                for(Piece p : (Piece[])pieces.toArray()){
                    if(p instanceof King){
                        if(kingCanMove) continue;
                    }

                    for(Coord c : (Coord[]) p.getMoveList().toArray()){

                        if(!threater.getThreater().getCoord().isEquals(c)){
                            p.getMoveList().remove(c);
                        }
                    }
                }
            }else if(threater.getIsThreater() == true){
                Coord[] blocks = getAllBlocksCoords(threater.getThreater());


                for(Piece p : (Piece[])pieces.toArray()){
                    if(p instanceof King){
                        if(kingCanMove) continue;
                    }
                    for(Coord c : (Coord[]) p.getMoveList().toArray()){
                        boolean remove = true;
                        for(Coord b : blocks){
                            if(c.isEquals(b)){
                                remove = false;
                            }
                        }
                        if(remove){
                            p.getMoveList().remove(c);
                        }
                    }
                }

            }

            if (!this.mate){
                Arquivo.addText(king.getCoord().coordJToString() + king.getCoord().coordIToString() + "+"); //adicionou no arquivo de jogada
            }
        }

        for(Piece p : (Piece[])pieces.toArray()){
            if(p.getMovements().length >= 1) noMoves = false;
        }

        if(noMoves){
            if(inCheck){
                mate = true;
            }else{
                staleMate = true;
            }
        }


        //Flags de fim de jogo
        if(staleMate){
            System.out.println("Stalemate!"); //Adiocionar flag em excpetion
        }

        if(mate){
            System.out.println("Mate!"); //Adionar flag em excpetion
        }

        setPlatesThatPlayerCanUse();


    }

    private Coord[] getAllBlocksCoords(Piece threater){
        MoveList blocks = new MoveList();

        if(threater instanceof Rook){
            attBlocksRook(blocks, threater.getCoord());
        }
        else if(threater instanceof Bishop){
            attBlocksBishop(blocks, threater.getCoord());
        }
        else if(threater instanceof Queen){
            attBlocksBishop(blocks, threater.getCoord());
            attBlocksRook(blocks, threater.getCoord());
        }


        return (Coord[])blocks.toArray();
    }



    private void attBlocksRook(MoveList blocks, Coord threaterCoord){
        int y = king.getCoord().i - threaterCoord.i;
        int x = king.getCoord().j - threaterCoord.j;

        if(x == 0){
            //Torre em baixo
            if(y > 0){
                for(int i = 1 ; i <= y ; ++i){
                    blocks.add(new Coord(king.getCoord().i - i, king.getCoord().j));
                }
            }
            //Torre em cima
            else if( y < 0){
                y *= -1;
                for(int i = 1 ; i <= y ; ++i){
                    blocks.add(new Coord(king.getCoord().i + i, king.getCoord().j));
                }
            }

        }else if(y == 0){

            //Torre na esquerda
            if(x > 0){
                for(int j = 1 ; j <= x ; ++j){
                    blocks.add(new Coord(king.getCoord().i, king.getCoord().j - j));
                }
            }
            //Torre na direita
            else if( x < 0){
                x *= -1;
                for(int j = 1 ; j <= x ; ++j){
                    blocks.add(new Coord(king.getCoord().i , king.getCoord().j + j));
                }
            }

        }
    }

    private void attBlocksBishop(MoveList blocks, Coord threaterCoord){
        int bi = threaterCoord.i;
        int bj = threaterCoord.j;
        int ki = king.getCoord().i;
        int kj = king.getCoord().j;


        blocks.add(new Coord(bi, bj));

        //bispo superior esquerda
        if(kj > bj && ki > bi){
            bi++;
            bj++;
            while( kj >= bj && ki >= bi ){
                blocks.add(new Coord(bi, bj));
                bi++;
                bj++;
            }
        }
        //bispo inferior esquerda
        else if( kj > bj && ki < bi ){
            bi--;
            bj++;
            while( kj >= bj && ki <= bi ){
                blocks.add(new Coord(bi, bj));
                bi--;
                bj++;
            }
        }
        //bispo superior direita
        else if( kj < bj && ki > bi ){
            bi++;
            bj--;
            while( kj <= bj && ki >= bi ){
                blocks.add(new Coord(bi, bj));
                bi++;
                bj--;
            }
        }
        //bispo inferior direita
        else if( kj < bj && ki < bi ){
            bi--;
            bj--;
            while( kj <= bj && ki <= bi ){
                blocks.add(new Coord(bi, bj));
                bi--;
                bj--;
            }
        }
    }


}

