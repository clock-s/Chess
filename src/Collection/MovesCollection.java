package Collection;

public interface MovesCollection {
    void add(Object move);
    void add(Object[] move);
    void remove(Object move);
    void clear();
    Object[] toArray();

}
