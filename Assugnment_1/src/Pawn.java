public class Pawn extends ConcretePiece{

    public Pawn(Player player)//helloadawd
    {
        this.player=player;
        if(this.player.isPlayerOne())
        {
            this.type="♟";
        }
        else
        {
            this.type="♙";
        }

    }

}
