package Utilities;

public class Coord {
    public int i;
    public int j;

    public Coord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Coord(Coord coord){
        this.i = coord.i;
        this.j = coord.j;
    }

    public Coord(){
        this.i = -1;
        this.j = -1;
    }


    public void setCoord(Coord coor){
        i = coor.i;
        j = coor.j;
    }

    public void setCoord(int i, int j){
        this.i = i;
        this.j = j;
    }

    public boolean isEquals(Coord coord) {
        if(i == coord.i && j == coord.j){
            return true;
        }else{
            return false;
        }
    }

}
