package Table;

import GUI.GUI;
import GUI.Images.PieceImages;
import Piece.*;
import Player.Player;
import Utilities.Color;
import Utilities.Coord;

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
        playerW = new Player(Color.WHITE, this, gui);
        playerB = new Player(Color.BLACK, this, gui);


        Coord coord = new Coord(0, 0);

        putPieces(coord);
        playerW.getEnemyPieces();
        playerB.getEnemyPieces();

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(table[i][j] != null) {
                    coord = new Coord(i, j);
                    gui.getPlate(coord).setIcon(table[i][j].getIcon());
                }
            }
        }

        gui.init();
        playerW.setRound(true);
        playerW.round();

    }

    private void putPieces(Coord coord) {
        byte indexBlack = 0;
        byte indexWhite = 0;

        ///WHITE PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(6, i);
            table[6][i] = new Pawn(indexWhite++, Color.WHITE,  images.WPeon, coord, this);
        }

        //Knight
        coord = new Coord(7, 1);
        table[coord.i][coord.j] = new Knight(indexWhite++,Color.WHITE, images.WKnight, coord, this);

        coord = new Coord(7, 6);
        table[coord.i][coord.j] = new Knight(indexWhite++,Color.WHITE, images.WKnight, coord, this);

        //Rook
        coord = new Coord(7, 0);
        table[coord.i][coord.j] = new Rook(indexWhite++,Color.WHITE, images.WRook, coord, this);

        coord = new Coord(7, 7);
        table[coord.i][coord.j] = new Rook(indexWhite++,Color.WHITE, images.WRook, coord, this);

        //Bishop
        coord = new Coord(7, 2);
        table[coord.i][coord.j] = new Bishop(indexWhite++,Color.WHITE, images.WBishop, coord, this);

        coord = new Coord(7, 5);
        table[coord.i][coord.j] = new Bishop(indexWhite++,Color.WHITE, images.WBishop, coord, this);

        //Queen
        coord = new Coord(7, 3);
        table[coord.i][coord.j] = new Queen(indexWhite++,Color.WHITE, images.WQueen, coord, this);

        //King
        coord = new Coord(7, 4);
        table[coord.i][coord.j] = new King(indexWhite++,Color.WHITE, images.WKing, coord, this, playerW);
        playerW.setKing(table[coord.i][coord.j]);


        ///BLACK PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(1, i);
            table[1][i] = new Pawn(indexBlack++,Color.BLACK,  images.BPeon, coord, this);
        }

        //Knight
        coord = new Coord(0, 1);
        table[coord.i][coord.j] = new Knight(indexBlack++, Color.BLACK, images.BKnight, coord, this);

        coord = new Coord(0, 6);
        table[coord.i][coord.j] = new Knight(indexBlack++, Color.BLACK, images.BKnight, coord, this);

        //Rook
        coord = new Coord(0, 0);
        table[coord.i][coord.j] = new Rook(indexBlack++, Color.BLACK, images.BRook, coord, this);

        coord = new Coord(0, 7);
        table[coord.i][coord.j] = new Rook(indexBlack++,Color.BLACK, images.BRook, coord, this);

        //Bishop
        coord = new Coord(0, 2);
        table[coord.i][coord.j] = new Bishop(indexBlack++,Color.BLACK, images.BBishop, coord, this);

        coord = new Coord(0, 5);
        table[coord.i][coord.j] = new Bishop(indexBlack++,Color.BLACK, images.BBishop, coord, this);

        //Queen
        coord = new Coord(0, 3);
        table[coord.i][coord.j] = new Queen(indexBlack++,Color.BLACK, images.BQueen, coord, this);

        //King
        coord = new Coord(0, 4);
        table[coord.i][coord.j] = new King(indexBlack++,Color.BLACK, images.BKing, coord, this, playerB);
        playerB.setKing(table[coord.i][coord.j]);
    }


    public Piece[][] getMap() {
        return table;
    }



    public void newPiecePosition(Coord place, Coord goal){
        if(table[goal.i][goal.j] != null) {
            table[goal.i][goal.j].death();
        }

        gui.getPlate(place).setIcon(null);
        gui.getPlate(goal).setIcon(table[place.i][place.j].getIcon());

        table[place.i][place.j].setCoord(goal);
        table[place.i][place.j].incrementMoves();

        this.table[goal.i][goal.j] = table[place.i][place.j];
        this.table[place.i][place.j] = null;

        /*
        swapRound = !swapRound;

        if(swapRound){
            playerB.round();
        }else{
            playerW.round();
        }
         */

        if(playerW.isRound()){
            playerW.setRound(false);
            playerB.setRound(true);
            playerB.round();
        }else{
            playerB.setRound(false);
            playerW.setRound(true);
            playerW.round();
        }

    }

    /*

    public Piece[] checkTable(Color color){
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
