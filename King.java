public class King extends ConcretePiece {
    public King(Player player)
    {
        this.places = new Stack<Position>();
        this.player=player;
        this.type="♔";
    }
}
