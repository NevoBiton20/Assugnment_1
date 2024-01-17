import java.util.Stack;

public abstract class ConcretePiece implements Piece{
    protected Player player;
    protected String type;
    protected Stack<Position> places;

    public Stack<Position> getPlaces() {
        return places;
    }
    @Override
    public Player getOwner() {
        return this.player;
    }

    @Override
    public String getType() {
        return this.type;
    }


}
