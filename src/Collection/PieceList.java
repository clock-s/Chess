package Collection;

import Piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class PieceList implements MovesCollection {

    private List<Piece> pieces = new ArrayList<Piece>();


    @Override
    public void add(Object object) {
        this.pieces.add((Piece) object);
    }

    @Override
    public void add(Object[] object) {
       for(Piece piece : (Piece[]) object) {
           this.pieces.add(piece);
       }
    }

    @Override
    public void remove(Object object) {
        this.pieces.remove((Piece) object);
    }

    @Override
    public void clear() {
        this.pieces.clear();
    }

    @Override
    public Object[] toArray() {
        return this.pieces.toArray(new Piece[this.pieces.size()]);
    }
}
