package Player;

import Collection.PieceList;
import GUI.GUI;
import Table.Table;
import Utilities.Category;
import Utilities.Color;
import Utilities.Coord;
import Piece.Piece;
import Utilities.PlateState;

public class Player{
    final private Color color;
    final private Table table;
    private boolean inCheck = false;
    private boolean isRound = false;
    final private GUI gui;
    private Piece king;
    private static Category choice = null;
    private PieceList pieces = new PieceList();


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

    public boolean isRound(){
        return isRound;
    }

    public void setRound(boolean round){
        isRound = round;
    }

    private void setPlatesThatPlayerCanUse() {
        Coord coord = new Coord();
        for(int i = 0; i < table.getMap().length; ++i){
            for(int j = 0; j < table.getMap()[i].length; ++j){
                coord.setCoord(i,j);

                if(table.getPlate(coord) != null && table.getPlate(coord).getColor() != this.color){
                    gui.getPlate(coord).setIsEnable(false);
                }else{
                    gui.getPlate(coord).setIsEnable(true);
                }
                gui.getPlate(coord).resetClick();
            }
        }
    }

    public void setKing(Piece king){
        this.king = king;
    }








    public void round(){
        gui.resetCoord();
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
