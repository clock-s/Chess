package Table;

import GUI.GUI;
import GUI.Images.PieceImages;
import Piece.Bishop.Bishop;
import Piece.King.King;
import Piece.Knight.Knight;
import Piece.Peon.Peon;
import Piece.Piece;
import Piece.Coord;
import Piece.Queen.Queen;
import Piece.Rook.Rook;
import Player.Player;

public class Table {
    private Piece[][] table;
    private GUI gui;
    private PieceImages images;
    private Player playerW;
    private Player playerB;
    private boolean swapRound = false;


    public Piece getPlate(Coord coord) {
        return table[coord.i][coord.j];
    }


    public Table() {
        table = new Piece[8][8];
        gui = new GUI(this);
        images = new PieceImages(gui.getImageSize());
        playerW = new Player('w', this, gui);
        playerB = new Player('b', this, gui);

        Coord coord = new Coord(0, 0);

        putPieces(coord);

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(table[i][j] != null) {
                    coord = new Coord(i, j);
                    gui.getPlate(coord).setIcon(table[i][j].getIcon());
                }
            }
        }

        gui.init();

        playerW.round();

    }

    private void putPieces(Coord coord) {

        ///WHITE PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(6, i);
            table[6][i] = new Peon('w',  images.WPeon, coord, this);
        }

        //Knight
        coord = new Coord(7, 1);
        table[coord.i][coord.j] = new Knight('w', images.WKnight, coord, this);

        coord = new Coord(7, 6);
        table[coord.i][coord.j] = new Knight('w', images.WKnight, coord, this);

        //Rook
        coord = new Coord(7, 0);
        table[coord.i][coord.j] = new Rook('w', images.WRook, coord, this);

        coord = new Coord(7, 7);
        table[coord.i][coord.j] = new Rook('w', images.WRook, coord, this);

        //Bishop
        coord = new Coord(7, 2);
        table[coord.i][coord.j] = new Bishop('w', images.WBishop, coord, this);

        coord = new Coord(7, 5);
        table[coord.i][coord.j] = new Bishop('w', images.WBishop, coord, this);

        //Queen
        coord = new Coord(7, 3);
        table[coord.i][coord.j] = new Queen('w', images.WQueen, coord, this);

        //King
        coord = new Coord(7, 4);
        table[coord.i][coord.j] = new King('w', images.WKing, coord, this);







        ///BLACK PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(1, i);
            table[1][i] = new Peon('b',  images.BPeon, coord, this);
        }

        //Knight
        coord = new Coord(0, 1);
        table[coord.i][coord.j] = new Knight('b', images.BKnight, coord, this);

        coord = new Coord(0, 6);
        table[coord.i][coord.j] = new Knight('b', images.BKnight, coord, this);

        //Rook
        coord = new Coord(0, 0);
        table[coord.i][coord.j] = new Rook('b', images.BRook, coord, this);

        coord = new Coord(0, 7);
        table[coord.i][coord.j] = new Rook('b', images.BRook, coord, this);

        //Bishop
        coord = new Coord(0, 2);
        table[coord.i][coord.j] = new Bishop('b', images.BBishop, coord, this);

        coord = new Coord(0, 5);
        table[coord.i][coord.j] = new Bishop('b', images.BBishop, coord, this);

        //Queen
        coord = new Coord(0, 3);
        table[coord.i][coord.j] = new Queen('b', images.BQueen, coord, this);

        //King
        coord = new Coord(0, 4);
        table[coord.i][coord.j] = new King('b', images.BKing, coord, this);

    }


    public Piece[][] getMap() {
        return table;
    }



    public void newPiecePosition(Coord place, Coord goal){
        gui.getPlate(place).setIcon(null);
        gui.getPlate(goal).setIcon(table[place.i][place.j].getIcon());

        table[place.i][place.j].setCoord(goal);
        table[place.i][place.j].incrementMoves();

        this.table[goal.i][goal.j] = table[place.i][place.j];
        this.table[place.i][place.j] = null;

        swapRound = !swapRound;

        if(swapRound){
            playerB.round();
        }else{
            playerW.round();
        }

    }

    /*
    public Piece[] checkTable(char color){
        Piece[] pieces = new Piece[16];
        int index = 0;

        for(Piece[] i : table){
            for(Piece p : i){
                if(p != null){
                    if(p.getColor() == color){
                        pieces[index] = p;
                        ++index;
                    }
                }
            }
        }
        return pieces;
    }

    public void newPiecePosition(Piece piece, byte movement){
        Coord acutalCoord  = piece.getCoord();
        Coord move = piece.move()[movement];

        gui.getPlate(acutalCoord).setIcon(null);
        gui.getPlate(move).setIcon(piece.getIcon());
        piece.setCoord(move);
        piece.incrementMoves();

        this.table[acutalCoord.i][acutalCoord.j] = null;
        this.table[move.i][move.j] = piece;
    }

    public void showTable(){
        for(int i = 0 ; i < table.length ; i++){
            for(int j = 0 ; j < table[i].length ; j++){
                if(table[i][j] != null){
                    System.out.printf(" " + table[i][j].getColor() + table[i][j].getType() + " ");
                } else{
                    System.out.printf("    ");
                }
                if(j != table[i].length - 1){
                    System.out.printf("|");
                }
            }
            if(i != table.length - 1){
                System.out.printf("\n---------------------------------------\n");
            }
        }
    }
     */




}
