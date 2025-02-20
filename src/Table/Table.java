package Table;

import GUI.GUI;
import GUI.Images.PieceImages;
import GUI.Promotion.PromotionPopUp;
import Piece.*;
import Player.Player;
import Utilities.Category;
import Utilities.Color;
import Utilities.Coord;

public class Table {
    final public static byte LENGHT = 8;

    private EsqueletonPlate[][] table;
    private GUI gui;
    private PieceImages images;
    private Player playerW;
    private Player playerB;
    private boolean swapRound = false;


    public Piece getPlate(Coord coord) {
        return table[coord.i][coord.j].getPiece();
    }

    public void clearAll(){
        for(byte i = 0; i < LENGHT; i++){
            for(byte j = 0; j < LENGHT; j++){
                table[i][j].clearAll();
            }
        }
    }


    public Table() {
        table = new EsqueletonPlate[LENGHT][LENGHT];
        gui = new GUI(this);
        images = new PieceImages();
        playerW = new Player(Color.WHITE, this, gui);
        playerB = new Player(Color.BLACK, this, gui);


        Coord coord = new Coord(0, 0);

        putPieces(coord);


        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(table[i][j].getPiece() != null) {
                    coord = new Coord(i, j);
                    gui.getPlate(coord).setIcon(table[i][j].getPiece().getIcon());
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


        for(int i = 0; i < LENGHT; ++i) {
            for(int j = 0; j < LENGHT; ++j) {
                table[i][j] = new EsqueletonPlate();
            }
        }


        ///WHITE PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(6, i);
            table[6][i].setPiece(new Pawn(indexWhite++, Color.WHITE,  PieceImages.WPawn, coord, this));
        }


        //Knight
        coord = new Coord(7, 1);
        table[coord.i][coord.j].setPiece(new Knight(indexWhite++,Color.WHITE, PieceImages.WKnight, coord, this));

        coord = new Coord(7, 6);
        table[coord.i][coord.j].setPiece(new Knight(indexWhite++,Color.WHITE, PieceImages.WKnight, coord, this));

        //Rook
        coord = new Coord(7, 0);
        table[coord.i][coord.j].setPiece(new Rook(indexWhite++,Color.WHITE, PieceImages.WRook, coord, this));

        coord = new Coord(7, 7);
        table[coord.i][coord.j].setPiece(new Rook(indexWhite++,Color.WHITE, PieceImages.WRook, coord, this));

        //Bishop
        coord = new Coord(7, 2);
        table[coord.i][coord.j].setPiece(new Bishop(indexWhite++,Color.WHITE, PieceImages.WBishop, coord, this));

        coord = new Coord(7, 5);
        table[coord.i][coord.j].setPiece(new Bishop(indexWhite++,Color.WHITE, PieceImages.WBishop, coord, this));

        //Queen
        coord = new Coord(7, 3);
        table[coord.i][coord.j].setPiece(new Queen(indexWhite++,Color.WHITE, PieceImages.WQueen, coord, this));

        //King
        coord = new Coord(7, 4);
        table[coord.i][coord.j].setPiece(new King(indexWhite++,Color.WHITE, PieceImages.WKing, coord, this, playerW));
        playerW.setKing(table[coord.i][coord.j].getPiece());

        for(byte i  = 6 ; i < Table.LENGHT ; i++){
            for(byte j = 0 ; j < Table.LENGHT ; j++){
                playerW.putPieceInList(table[i][j].getPiece());
            }
        }


        ///BLACK PIECES
        //Peon
        for(int i = 0; i < 8; i++) {
            coord = new Coord(1, i);
            table[1][i].setPiece(new Pawn(indexBlack++,Color.BLACK,  PieceImages.BPawn, coord, this));
        }

        //Knight
        coord = new Coord(0, 1);
        table[coord.i][coord.j].setPiece(new Knight(indexBlack++, Color.BLACK, PieceImages.BKnight, coord, this));

        coord = new Coord(0, 6);
        table[coord.i][coord.j].setPiece(new Knight(indexBlack++, Color.BLACK, PieceImages.BKnight, coord, this));

        //Rook
        coord = new Coord(0, 0);
        table[coord.i][coord.j].setPiece(new Rook(indexBlack++, Color.BLACK, PieceImages.BRook, coord, this));

        coord = new Coord(0, 7);
        table[coord.i][coord.j].setPiece(new Rook(indexBlack++,Color.BLACK, PieceImages.BRook, coord, this));

        //Bishop
        coord = new Coord(0, 2);
        table[coord.i][coord.j].setPiece(new Bishop(indexBlack++,Color.BLACK, PieceImages.BBishop, coord, this));

        coord = new Coord(0, 5);
        table[coord.i][coord.j].setPiece(new Bishop(indexBlack++,Color.BLACK, PieceImages.BBishop, coord, this));

        //Queen
        coord = new Coord(0, 3);
        table[coord.i][coord.j].setPiece(new Queen(indexBlack++,Color.BLACK, PieceImages.BQueen, coord, this));

        //King
        coord = new Coord(0, 4);
        table[coord.i][coord.j].setPiece(new King(indexBlack++,Color.BLACK, PieceImages.BKing, coord, this, playerB));
        playerB.setKing(table[coord.i][coord.j].getPiece());


        for(byte i  = 0 ; i < 1 ; i++){
            for(byte j = 0 ; j < Table.LENGHT ; j++){
                playerB.putPieceInList(table[i][j].getPiece());
            }
        }


    }


    public EsqueletonPlate[][] getMap() {
        return table;
    }



    public void newPiecePosition(Coord place, Coord goal){
        if(table[goal.i][goal.j].getPiece() != null) {
            table[goal.i][goal.j].getPiece().death();
        }


        gui.getPlate(place).setIcon(null);
        gui.getPlate(goal).setIcon(table[place.i][place.j].getPiece().getIcon());

        table[place.i][place.j].getPiece().setCoord(goal);
        table[place.i][place.j].getPiece().incrementMoves();

        this.table[goal.i][goal.j].setPiece(table[place.i][place.j].getPiece());
        if (promotionZone(table[goal.i][goal.j].getPiece().getCoord())) {
            PromotionPopUp promotionPopUp = new PromotionPopUp(gui.getFrame());
        }
        this.promotionPiece(table[goal.i][goal.j].getPiece().getCoord());

        this.table[place.i][place.j].removePiece();

        /*
        swapRound = !swapRound;

        if(swapRound){
            playerB.round();
        }else{
            playerW.round();
        }
         */

        this.clearAll();


        /*
        Etapas:
        1 - Limpa
        2 - Coloca as casa perigosas do jogador inimigo
        3 - Começa o round do jogador
        4 - Acaba
        5 - Volta para 1

         */

        if(playerW.isRound()){
            playerW.setRound(false);
            playerB.setRound(true);
            playerW.setStatusPlates();
            playerB.round();

        }else{
            playerB.setRound(false);
            playerW.setRound(true);
            playerB.setStatusPlates();
            playerW.round();
        }



    }

    public boolean promotionZone(Coord place) {
        boolean promotionEnabled = false;
        if (table[place.i][place.j].getPiece() instanceof Pawn){
            if (table[place.i][place.j].getPiece().getColor() == Color.WHITE) {
                if (table[place.i][place.j].getPiece().getCoord().i == 0) {
                    promotionEnabled = true;
                }
            }
            if (table[place.i][place.j].getPiece().getColor() == Color.BLACK) {
                if (table[place.i][place.j].getPiece().getCoord().i == Table.LENGHT-1) {
                    promotionEnabled = true;
                }
            }
        }
        return promotionEnabled;
    }

    public void promotionPiece(Coord place){
        if (this.promotionZone(place)){
            if(table[place.i][place.j].getPiece().getColor() == Color.WHITE){

                playerW.removePieceFromList(table[place.i][place.j].getPiece());

                if (Player.getChoice() == Category.BISHOP){
                    table[place.i][place.j].setPiece(new Bishop(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WBishop,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.ROOK){
                    table[place.i][place.j].setPiece(new Rook(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WRook,table[place.i][place.j].getPiece().getCoord(), this));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.KNIGHT){
                    table[place.i][place.j].setPiece(new Knight(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WKnight,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.QUEEN){
                    table[place.i][place.j].setPiece(new Queen(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WQueen,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }

                playerW.putPieceInList(table[place.i][place.j].getPiece());

            }else if (table[place.i][place.j].getPiece().getColor() == Color.BLACK){

                playerB.removePieceFromList(table[place.i][place.j].getPiece());

                if (Player.getChoice() == Category.BISHOP){
                    table[place.i][place.j].setPiece(new Bishop(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BBishop,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.ROOK){
                    table[place.i][place.j].setPiece(new Rook(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BRook,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.KNIGHT){
                    table[place.i][place.j].setPiece(new Knight(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BKnight,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }
                if (Player.getChoice() == Category.QUEEN){
                    table[place.i][place.j].setPiece(new Queen(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BQueen,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                }

                playerB.putPieceInList(table[place.i][place.j].getPiece());
            }
        }
        Player.setChoice(null);
    }



}
