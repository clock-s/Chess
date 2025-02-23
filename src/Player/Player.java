package Player;

import Collection.PieceList;
import GUI.GUI;
import Table.Table;
import Utilities.*;
import Piece.Piece;




public class Player{
    final private Color color;
    final private Table table;
    private boolean inCheck = false;
    final private GUI gui;
    private Piece king;
    private static Category choice = null;
    private PieceList pieces = new PieceList();
    private Coord[] clicks = new Coord[2];

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
            Coord[] danger = p.getDangerZone();
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
        this.king = king;
    }





    public void round(){

        setPlatesThatPlayerCanUse();


        if(table.getMap()[king.getCoord().i][king.getCoord().j].getPlateSate() == PlateState.DANGER){
            System.out.println("Player " + color.toString() + " in check");
            inCheck = true;
        }

        if(inCheck){
            if(table.getMap()[king.getCoord().i][king.getCoord().j].getPlateSate() != PlateState.DANGER){
                inCheck = false;
                System.out.println("Player " + color.toString() + " run the check");
            }
        }

    }
}

