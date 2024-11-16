package Player;

import GUI.GUI;
import Table.Table;
import Piece.Coord;

public class Player{
    final private char color;
    final private Table table;
    final private GUI gui;

    public Player(char color, Table table, GUI gui) {
        this.color = color;
        this.table = table;
        this.gui = gui;
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

    public void round(){
        gui.resetCoord();
        setPlatesThatPlayerCanUse();


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
