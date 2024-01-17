import java.util.Stack;

public class ConcretePlayer implements Player{
    private int wins;
    private final boolean playerSide;
    private Stack<Piece[]> eaten;

    public ConcretePlayer(boolean playerSide) {
        this.playerSide = playerSide;
        this.wins=0;
        eaten = new Stack<Piece[]>();
    }

    @Override
    public boolean isPlayerOne() {
        return this.playerSide;
    }

    @Override
    public int getWins() {
        return this.wins;
    }
}
