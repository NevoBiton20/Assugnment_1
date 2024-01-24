public class Position {
    private final int row;
    private final int cul;

    public Position(int row, int cul) {
        this.row = row;
        this.cul = cul;
    }

    public int getRow() {
        return this.row;
    }

    public int getCul() {
        return this.cul;
    }

    public String toString() {
        return "(" + this.row + ", " + this.cul + "), ";
    }
}


