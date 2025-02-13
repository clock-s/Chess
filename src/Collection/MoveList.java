package Collection;

import Utilities.Coord;

import java.util.ArrayList;
import java.util.List;

public class MoveList implements MovesCollection{

    private List<Coord> moves = new ArrayList<Coord>();

    @Override
    public void add(Object object) {
        this.moves.add((Coord)object);
    }

    @Override
    public void add(Object[] object) {
        for (Coord coord : (Coord[]) object) {
            this.add(coord);
        }
    }

    @Override
    public void remove(Object object) {
        this.moves.remove((Coord)object);
    }

    @Override
    public void clear() {
        this.moves.clear();
    }

    @Override
    public Object[] toArray() {
        return moves.toArray(new Coord[moves.size()]);
    }



}
