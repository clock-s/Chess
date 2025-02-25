package Table;

import Collection.PieceList;
import Utilities.PlateState;
import Piece.Piece;

public class EsqueletonPlate {
    private Piece piece;
    private PlateState plateSate;
    private PieceList threaters;

    public EsqueletonPlate() {
        this.piece = null;
        this.plateSate = PlateState.SAFE;
        this.threaters = new PieceList();
    }

    public Piece getPiece() {return this.piece;}

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public PlateState getPlateState() {return this.plateSate;}

    public void setPlateState(PlateState newState) {
        if(newState == PlateState.SAFE){
            this.plateSate = newState;
            return;
        }

        if(this.plateSate == PlateState.SAFE){
            this.plateSate = newState;
        }else if((this.plateSate == PlateState.DANGER || this.plateSate == PlateState.POTENTIAL ) && newState != this.plateSate){
            this.plateSate = PlateState.BOTH;
        }

        return;
    }

    public Piece[] getTheters() {return (Piece[])this.threaters.toArray();}
    public void setThreaters(Piece piece) {this.threaters.add(piece);}


    public void clearAll(){
        this.threaters.clear();
        this.plateSate = PlateState.SAFE;
    }

    public void removePiece(){
        this.piece = null;
    }


}
