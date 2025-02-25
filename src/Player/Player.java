package Player;

import Collection.PieceList;
import GUI.GUI;
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

public class Player{
    final private Color color;
    final private Table table;
    private boolean inCheck = false;
    private boolean isRound = false;
    final private GUI gui;
    private King king;
    private static Category choice = null;
    private PieceList pieces = new PieceList();
    private Coord[] clicks = new Coord[2];
    private boolean mate = false;
    private boolean staleMate = true;

    public Coord[] getClicks() {
        return clicks;
    }

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
            System.out.println(threater.getThreater().getClass().getName());
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
                System.out.printf("Aki");
                MoveList blocks = new MoveList();


                if(threater.getThreater() instanceof Rook){
                    int y = king.getCoord().i - threater.getThreater().getCoord().i;
                    int x = king.getCoord().j - threater.getThreater().getCoord().j;
                    System.out.printf("%d %d\n", x, y);

                    if(x == 0){
                        if(y > 0){
                            for(int i = 1 ; i <= y ; ++i){
                                blocks.add(new Coord(king.getCoord().i - i, king.getCoord().j));
                            }
                        }else if( y < 0){
                            y *= -1;
                            for(int i = 1 ; i <= y ; ++i){
                                blocks.add(new Coord(king.getCoord().i + i, king.getCoord().j));
                            }
                        }

                    }else if(y == 0){
                        if(x > 0){
                            System.out.println("Aki");
                            for(int j = 1 ; j <= x ; ++j){
                                blocks.add(new Coord(king.getCoord().i, king.getCoord().j - j));
                            }
                        }else if( x < 0){
                            x *= -1;
                            for(int j = 1 ; j <= x ; ++j){
                                blocks.add(new Coord(king.getCoord().i , king.getCoord().j + j));
                            }
                        }

                    }


                }
                else if(threater.getThreater() instanceof Bishop){
                    int bi = threater.getThreater().getCoord().i;
                    int bj = threater.getThreater().getCoord().j;
                    int ki = king.getCoord().i;
                    int kj = king.getCoord().j;
                    //bispo superior esquerda
                    if(kj > bj && ki > bi){
                        bi++;
                        bj++;
                        while( kj > bj && ki > bi ){
                            blocks.add(new Coord(bi, bj));
                            bi++;
                            bj++;
                        }
                    }
                    //bispo inferior esquerda
                    else if( kj > bj && ki < bi ){
                        bi--;
                        bj++;
                        while( kj > bj && ki < bi ){
                            blocks.add(new Coord(bi, bj));
                            bi--;
                            bj++;
                        }
                    }
                    //bispo superior direita
                    else if( kj < bj && ki > bi ){
                        bi++;
                        bj--;
                        while( kj < bj && ki > bi ){
                            blocks.add(new Coord(bi, bj));
                            bi++;
                            bj--;
                        }
                    }
                    //bispo inferior direita
                    else if( kj < bj && ki < bi ){
                        bi--;
                        bj--;
                        while( kj < bj && ki < bi ){
                            blocks.add(new Coord(bi, bj));
                            bi--;
                            bj--;
                        }
                    }
                }

                for(Piece p : (Piece[])pieces.toArray()){
                    if(p instanceof King){
                        if(kingCanMove) continue;
                    }
                    for(Coord c : (Coord[]) p.getMoveList().toArray()){
                        boolean remove = true;
                        for(Coord b : (Coord[]) blocks.toArray()){
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
        }else{
            for(Piece p : (Piece[])pieces.toArray()){
                if(p.getMovements().length >= 1) staleMate = false;
            }
        }

        if(staleMate == true){
            System.out.println("Stalemate!");
        }

        setPlatesThatPlayerCanUse();



    }
}

