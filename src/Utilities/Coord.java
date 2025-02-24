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

    public String coordIToString(){
        String flag = "";
        if (this.i == 0){
            flag = "8";
        }
        if (this.i == 1){
            flag = "7";
        }
        if (this.i == 2){
            flag = "6";
        }
        if (this.i == 3){
            flag = "5";
        }
        if (this.i == 4){
            flag = "4";
        }
        if (this.i == 5){
            flag = "3";
        }
        if (this.i == 6){
            flag = "2";
        }
        if (this.i == 7){
            flag = "1";
        }
        return flag;
    }

    public String coordJToString(){
        String flag ="";
        if (this.j == 0){
            flag = "a";
        }
        if (this.j == 1){
            flag = "b";
        }
        if (this.j == 2){
            flag = "c";
        }
        if (this.j == 3){
            flag = "d";
        }
        if (this.j == 4){
            flag = "e";
        }
        if (this.j == 5){
            flag = "f";
        }
        if (this.j == 6){
            flag = "g";
        }
        if (this.j == 7){
            flag = "h";
        }
        return flag;
    }

}
