public class King extends ConcretePiece {
    public King(Player player)
    {
        this.places = new Stack<>();
        this.player=player;
        this.type="♔";
    }
}
