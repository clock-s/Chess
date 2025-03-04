package Table;

import GUI.GUI;
import GUI.Images.PieceImages;
import GUI.Promotion.PromotionPopUp;
import Piece.*;
import Player.Player;
import Utilities.Category;
import Utilities.Color;
import Utilities.Coord;
import Utilities.PieceState;
import Storie.Arquivo;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Table extends JPanel {
    final public static byte LENGHT = 8;

    private EsqueletonPlate[][] table;
    private GUI gui;
    private PieceImages images;
    private Player playerW;
    private Player playerB;
    private static Color round = Color.WHITE;

    //booleano para marcar se comeu, se promoveu, se fez roque, ou se só andou martins hihi
    private PieceState estado;


    public Piece getPlate(Coord coord) {
        return table[coord.i][coord.j].getPiece();
    }

    public static Color getRound() {
        return round;
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

        setLayout(new BorderLayout()); // Define o layout do tabuleiro
        add(gui, BorderLayout.CENTER); // Adiciona a interface gráfica ao painel

        Arquivo.startNewGame();


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


        for(byte i  = 0 ; i < 2 ; i++){
            for(byte j = 0 ; j < Table.LENGHT ; j++){
                playerB.putPieceInList(table[i][j].getPiece());
            }
        }


    }


    public EsqueletonPlate[][] getMap() {
        return table;
    }



    public void newPiecePosition(Coord place, Coord goal){
        gui.resetCoord();

        this.estado = PieceState.NONE; //sempre começa na premissa de nao ter comido martins hihi

        if(table[goal.i][goal.j].getPiece() != null) {
            if(table[goal.i][goal.j].getPiece().getColor() == Color.BLACK) {
                playerB.removePieceFromList(table[goal.i][goal.j].getPiece());
            }else if(table[goal.i][goal.j].getPiece().getColor() == Color.WHITE) {
                playerW.removePieceFromList(table[goal.i][goal.j].getPiece());
            }
            table[goal.i][goal.j].getPiece().death();
            this.estado = PieceState.TAKED;
        }


        if (this.estado == PieceState.NONE){
            Arquivo.addText(table[place.i][place.j].getPiece().categoryToString() + goal.coordJToString() + goal.coordIToString());
            //adicionar o texto de movimento da peça martins hihi
        }
        if (this.estado == PieceState.TAKED){
            Arquivo.addText(table[place.i][place.j].getPiece().categoryToString() + "x" + goal.coordJToString() + goal.coordIToString());
            //adicionar o texto de comida martins hihi
        }


        gui.getPlate(place).setIcon(null);
        gui.getPlate(goal).setIcon(table[place.i][place.j].getPiece().getIcon());

        table[place.i][place.j].getPiece().setCoord(goal);
        table[place.i][place.j].getPiece().incrementMoves();

        this.table[goal.i][goal.j].setPiece(table[place.i][place.j].getPiece());
        if (promotionZone(table[goal.i][goal.j].getPiece().getCoord())) {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame) {
                PromotionPopUp promotionPopUp = new PromotionPopUp((JFrame) window);
            }
        }
        this.promotionPiece(table[goal.i][goal.j].getPiece().getCoord());

        this.rockEnabled(table[goal.i][goal.j].getPiece().getCoord());
        this.table[place.i][place.j].removePiece();


        this.clearAll();




        /*
        Etapas:
        1 - Limpa
        2 - Coloca as casa perigosas do jogador inimigo
        3 - Começa o round do jogador
        4 - Acaba
        5 - Volta para 1

         */


        if(round == Color.WHITE){

            round = Color.BLACK;

            playerW.setStatusPlates();
            playerB.round();

        }else{

            round = Color.WHITE;

            playerB.setStatusPlates();
            playerW.round();
        }



    }

    public void roqueSwap (Coord place, Coord goal){
        gui.getPlate(place).setIcon(null);
        gui.getPlate(goal).setIcon(table[place.i][place.j].getPiece().getIcon());

        table[place.i][place.j].getPiece().setCoord(goal);
        table[place.i][place.j].getPiece().incrementMoves();

        this.table[goal.i][goal.j].setPiece(table[place.i][place.j].getPiece());
        this.table[place.i][place.j].removePiece();
    }

    public void rockEnabled(Coord place){
        if(table[place.i][place.j].getPiece().getCategory() == Category.KING) {
            if (table[place.i][place.j].getPiece().getColor() == Color.BLACK) {
                if (table[place.i][place.j].getPiece().getCoord().i == 0 &&
                        table[place.i][place.j].getPiece().getCoord().j == 6) {
                    this.makeLitlleRockBlack(new Coord (0, 7));
                }

                if (table[place.i][place.j].getPiece().getCoord().i == 0 &&
                        table[place.i][place.j].getPiece().getCoord().j == 2) {
                    this.makeBigRockBlack(new Coord (0, 0));
                }
            }
            if (table[place.i][place.j].getPiece().getColor() == Color.WHITE) {
                if(table[place.i][place.j].getPiece().getCoord().i == 7 &&
                        table[place.i][place.j].getPiece().getCoord().j == 6){
                    this.makeLitlleRockWhite(new Coord (7, 7));
                }
                if(table[place.i][place.j].getPiece().getCoord().i == 7 &&
                        table[place.i][place.j].getPiece().getCoord().j == 2){
                    this.makeBigRockWhite(new Coord (7, 0));
                }
            }
        }
    }

    // função fazer o roque pequeno
    public void makeLitlleRockWhite(Coord placeRook){
        // se for possível fazer o roque e o jogador clicou duas casas a direita
        this.roqueSwap(placeRook, new Coord (7,5));
    }
    public void makeLitlleRockBlack(Coord placeRook){
        // se for possível fazer o roque e o jogador clicou duas casas a direita
        this.roqueSwap(placeRook, new Coord (0,5));
    }
    // função fazer o roque grande
    public void makeBigRockWhite(Coord placeRook){
        // se for possível fazer o roque e o jogador clicou duas a esquerda
        this.roqueSwap(placeRook, new Coord (7,3));
    }
    public void makeBigRockBlack(Coord placeRook){
        // se for possível fazer o roque e o jogador clicou duas a esquerda
        this.roqueSwap(placeRook, new Coord (0,3));
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
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "B");
                }
                if (Player.getChoice() == Category.ROOK){
                    table[place.i][place.j].setPiece(new Rook(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WRook,table[place.i][place.j].getPiece().getCoord(), this));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "R");
                }
                if (Player.getChoice() == Category.KNIGHT){
                    table[place.i][place.j].setPiece(new Knight(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WKnight,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "N");
                }
                if (Player.getChoice() == Category.QUEEN){
                    table[place.i][place.j].setPiece(new Queen(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.WQueen,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "Q");
                }

                playerW.putPieceInList(table[place.i][place.j].getPiece());

            }else if (table[place.i][place.j].getPiece().getColor() == Color.BLACK){

                playerB.removePieceFromList(table[place.i][place.j].getPiece());

                if (Player.getChoice() == Category.BISHOP){
                    table[place.i][place.j].setPiece(new Bishop(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BBishop,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "B");
                }
                if (Player.getChoice() == Category.ROOK){
                    table[place.i][place.j].setPiece(new Rook(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BRook,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "R");
                }
                if (Player.getChoice() == Category.KNIGHT){
                    table[place.i][place.j].setPiece(new Knight(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BKnight,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "N");
                }
                if (Player.getChoice() == Category.QUEEN){
                    table[place.i][place.j].setPiece(new Queen(table[place.i][place.j].getPiece().getId(), table[place.i][place.j].getPiece().getColor(), PieceImages.BQueen,table[place.i][place.j].getPiece().getCoord(), this ));
                    gui.getPlate(table[place.i][place.j].getPiece().getCoord()).setIcon(table[place.i][place.j].getPiece().getIcon());
                    Arquivo.addText(place.coordJToString() + place.coordIToString() + "=" + "Q");
                }

                playerB.putPieceInList(table[place.i][place.j].getPiece());
            }
        }
        Player.setChoice(null);
    }



}
