package Player;

import GUI.GUI;
import Table.Table;
import Piece.Coord;
import Piece.Piece;

public class Player{
    final private char color;
    final private Table table;
    private boolean inCheck = false;
    private boolean isRound = false;
    final private GUI gui;
    private Piece king;
    private Piece[] enemys = new Piece[16];
    private Coord[][] enemysDangerZone = new Coord[16][];

    public Player(char color, Table table, GUI gui) {
        this.color = color;
        this.table = table;
        this.gui = gui;
    }

    public char getColor() {
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
        for(int i = 0; i < table.getMap().length; ++i){
            for(int j = 0; j < table.getMap()[i].length; ++j){
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
            System.out.printf("%c in check", color);
        }

        /*
        for(byte i = 0 ; i < piece.length ; i++){
            if(piece[i] != null){
                System.out.printf("%c_%d:\n", piece[i].getType(), i+1);
                showMoves(piece[i]);
            }
        }
        System.out.printf("\n");

        Piece[] piece;
        piece = table.checkTable(color);



        System.out.println("\n" + this.color + " can move: ");
        makeAMove(piece);

        setPlatesThatPlayerCanUse(false);

        System.out.println("aaaa");
        */
    }

    /*
    public byte makeADecision(){
        Scanner playerDecision = new Scanner(System.in);

        byte wish = playerDecision.nextByte();

        return --wish;
    }

    private void showMoves(Piece p){
        byte count = 0;

        for(int i = 0 ; i < p.move().length ; i++){
            Coord to = p.move()[i];

            if(to != null){
                if(count != 0 && count%3 == 0){
                    System.out.println();
                }

                System.out.printf(" (%d,%d) --> (%d,%d) ", p.getCoord().i, p.getCoord().j, to.i, to.j);
                ++count;
            }
            if (i + 1 < p.move().length && p.move()[i+1] != null){
                System.out.printf("|");
            }
        }
        System.out.println();
    }

    private void makeAMove(Piece[] piece){
        byte decision;
        System.out.println("Which piece you want to move? (1 ~ 16 in order)");
        do{
            decision = makeADecision();
            if(decision < 0 || decision >= piece.length || piece[decision] == null){
                System.out.println("This piece doesn't exist");
            }
        }while(decision < 0 || decision >= piece.length || piece[decision] == null);


        byte movement;
        System.out.printf("The piece %c can do this moves: \n", piece[decision].getType());
        showMoves(piece[decision]);
        System.out.println("Choose your move(0 to back) : ");
        do{
            movement = makeADecision();

            if(movement == -1){
                round();
                return;
            }

            if(movement < 0 || movement >= piece[decision].move().length || piece[decision].move()[movement] == null){
                System.out.println("This move doesn't exist");
            }
        }while(movement < 0 || movement >= piece[decision].move().length || piece[decision].move()[movement] == null);


        this.table.newPiecePosition(piece[decision], movement);

        System.out.println("End of round.\n");

    }
     */


}
