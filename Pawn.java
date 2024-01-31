public class Pawn extends ConcretePiece{

    public Pawn(Player player)
    {
        this.places = new Stack<Position>();
        this.player=player;
        if(this.player.isPlayerOne())
        {
            this.type="♙";
        }
        else
        {
            this.type="♟";
        }
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
