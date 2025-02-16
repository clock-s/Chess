package Player;

import GUI.GUI;
import Table.Table;
import Utilities.Category;
import Utilities.Color;
import Utilities.Coord;
import Piece.Piece;

public class Player{
    final private Color color;
    final private Table table;
    private boolean inCheck = false;
    private boolean isRound = false;
    final private GUI gui;
    private Piece king;
    private Piece[] enemys = new Piece[16];
    private Coord[][] enemysDangerZone = new Coord[16][];
    private static Category choice = null;

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

    public void getEnemyPieces(){
        for(int i = 0; i < Table.LENGHT; ++i){
            for(int j = 0; j < Table.LENGHT; ++j){
                Coord coord = new Coord(i,j);
                if(table.getPlate(coord) != null){
                    if(table.getPlate(coord).getColor() != this.color){
                        enemys[table.getPlate(coord).getId()] = table.getPlate(coord);
                    }
                }
            }
        }
    }

    public void setDangerZone(){
        for(int i = 0 ; i < enemys.length; ++i){
            enemysDangerZone[i] = enemys[i].dangerZone();
        }
    }

    public Coord[][] getEnemysDangerZone(){
        setDangerZone();
        return enemysDangerZone;
    }

    private void deleteDeathEnemys(){
        for(int i = 0; i < enemys.length; ++i){
            if(!enemys[i].isAlive()){
                enemysDangerZone[i] = null;
            }
        }

    }

    private boolean isInCheck(){
        setDangerZone();
        for(Coord[] i : enemysDangerZone){
            if(i != null){
                for(Coord j : i){
                    if(j != null){
                        if(j.isEquals(king.getCoord())){
                            inCheck = true;
                            return true;
                        }
                    }
                }
            }
        }
        inCheck = false;
        return false;
    }

    public void round(){
        gui.resetCoord();
        setPlatesThatPlayerCanUse();
        deleteDeathEnemys();
        if(isInCheck()){
            System.out.printf("%c in check", (color == Color.BLACK ? 'B' : 'W') );
        }

    }



}
