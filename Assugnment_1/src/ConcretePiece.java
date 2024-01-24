import java.util.Stack;

public abstract class ConcretePiece implements Piece{


    protected  int distance = 0;
    protected Player player;
    protected String type;
    protected Stack<Position> places;
    protected String id;
    protected int killCount = 0;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void  addKill(){
        this.killCount++;
    }
    public int getKillCount(){
        return this.killCount;
    }
    public Stack<Position> getPlaces() {
        return places;
    }
    public void printPlaces(){
        while (!places.isEmpty()){
            System.out.println(places.pop());
        }
    }
    @Override
    public Player getOwner() {
        return this.player;
    }

    @Override
    public String getType() {
        return this.type;
    }
    public int getDistance() {
        return distance;
    }

    public void addDistance(int distance) {
        this.distance += distance;
    }



}
