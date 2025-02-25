package GUI;

import GUI.Plates.Plates;
import Utilities.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUI extends JPanel { // Agora GUI herda JPanel
    private JPanel table;
    private Plates[][] plate;
    private static int imageSize;
    final private Color color1 = new Color(238, 238, 212, 255);
    final private Color color2 = new Color(125, 148, 92, 255);
    private Table map;
    public Coord[] clicks;
    public byte count = 0;

    public static int getImageSize() {
        if (imageSize != 0) {
            return imageSize;
        }
        System.out.println("Image size not set");
        return 0;
    }

    public GUI(Table table) {
        this.map = table;
        this.table = new JPanel(new GridLayout(8, 8));
        this.plate = new Plates[8][8];
        clicks = new Coord[2];
        clicks[0] = new Coord();
        clicks[1] = new Coord();

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        configs();
        makeTable();

        add(this.table, BorderLayout.CENTER);
    }

    private void configs() {
        int tableSize = 600; // Tamanho padrão para o tabuleiro
        setPreferredSize(new Dimension(tableSize, tableSize));
    }

    public Piece getPiece(Coord coord) {
        return map.getPlate(coord);
    }

    public Table getTable() {
        return map;
    }

    private void makeTable() {
        int plateSize = getPreferredSize().width / 8;
        imageSize = plateSize;

        table.setPreferredSize(new Dimension(plateSize * 8, plateSize * 8));
        table.setBackground(new Color(184, 95, 14));
        table.setBorder(new LineBorder(new Color(184, 95, 14), 5));

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 == 0) {
                    setPlate(i, j, color1);
                } else {
                    setPlate(i, j, color2);
                }
            }
        }
    }

    public void resetCoord() {
        count = 0;
        clicks[0] = new Coord();
        clicks[1] = new Coord();
    }

    private void setPlate(int i, int j, Color color) {
        plate[i][j] = new Plates(color, new Coord(i, j), this);
        table.add(plate[i][j].getPanel());
    }

    public Plates getPlate(Coord coord) {
        return plate[coord.i][coord.j];
    }

    public void modifyColor(Coord coord, Color color) {
        plate[coord.i][coord.j].getPanel().setBackground(color);
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void countIncrease() {
        count = (byte) ((count + 1) % 2);
    }
}