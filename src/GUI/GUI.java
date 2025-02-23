package GUI;

import GUI.Plates.Plates;
import Utilities.Coord;
import Piece.Piece;
import Table.Table;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;



public class GUI{
    private JFrame frame;
    private ImageIcon image;
    private Border border;
    private JPanel table;
    private Plates[][] plate;
    private static int imageSize;
    final private Color color1 = new Color(238,238,212,255);
    final private Color color2 = new Color(125,148,92,255);
    private Table map;
    public Coord[] clicks;
    public byte count = 0;

    public static int getImageSize(){
        if(imageSize != 0){
            return imageSize;
        }
        System.out.println("Image size not set");
        return 0;
    }

    public GUI(Table table){
        this.frame = new JFrame();
        this.table = new JPanel(new GridLayout(8,8));
        this.plate = new Plates[8][8];
        this.map = table;
        clicks = new Coord[2];
        clicks[0] = new Coord();
        clicks[1] = new Coord();

        configs();
        makeTable();
    }

    private void configs(){
        frame.setTitle("Chess Test");
        frame.setSize(640,680);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        image = new ImageIcon("src/GUI/Images/logo.jpg");
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.gray);
        frame.setLayout(null);
        border = new LineBorder(Color.BLACK, 2);
    }

    public Piece getPiece(Coord coord){
        return map.getPlate(coord);
    }

    public Table getTable(){
        return map;
    }

    private void makeTable(){
        int plateSize = (frame.getWidth() - 20) / 8;
        imageSize = plateSize;

        table.setSize(plateSize*8, plateSize*8);
        table.setBackground(Color.BLACK);
        table.setLocation((frame.getSize().width - table.getWidth())/2,
                          (frame.getSize().width - table.getHeight())/2);
        table.setBorder(border);

        for(int i = 0 ; i < 8 ; ++i){
            for(int j = 0 ; j < 8 ; ++j){
                if((i+j)%2 == 0 ) {
                    setPlate(i, j, color1);
                }
                else {
                    setPlate(i, j, color2);
                }
            }
        }


        frame.add(table);
    }

    public void init(){
        frame.setVisible(true);
    }

    public void resetCoord(){
        count = 0;
        clicks[0] = new Coord();
        clicks[1] = new Coord();
    }

    private void setPlate(int i, int j, Color color){
        plate[i][j] = new Plates(color, new Coord(i,j), this);
        table.add(plate[i][j].getPanel());
    }

    public Plates getPlate(Coord coord) {
        return plate[coord.i][coord.j];
    }

    public void modifyColor(Coord coord, Color color){
        plate[coord.i][coord.j].getPanel().setBackground(color);
    }

    public Color getColor1(){
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void countIncrease(){
        count = (byte) ((count+1)%2);
    }

    public JFrame getFrame(){return this.frame;}

}
