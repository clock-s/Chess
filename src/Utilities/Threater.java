package Utilities;
import Piece.Piece;

public class Threater {
    private boolean isThreater;
    private Piece threater;

    public Threater( boolean isThreater, Piece threater) {
        this.threater = threater;
        this.isThreater = isThreater;
    }


    public boolean getIsThreater() {
        return isThreater;
    }

    public Piece getThreater() {
        return this.threater;
    }
}
