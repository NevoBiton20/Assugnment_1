import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Stack;

public class GameLogic implements PlayableLogic {
    private final int BOARD_SIZE = 11;
    private final ConcretePlayer p1 = new ConcretePlayer(false);
    private final ConcretePlayer p2 = new ConcretePlayer(true);
    private ConcretePiece[][] gameBoard;
    private Position[][] posC;
    private boolean turn;
    private Position kingP;
    private ArrayList<ConcretePiece> pieces;
    //private ArrayList<Position> steppedOn;

    public GameLogic() {
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        if (!isLegal(a, b)) {
            return false;
        }
        if (posC[a.getRow()][a.getCul()].getStepped() == 0) {
            posC[a.getRow()][a.getCul()].addStepped();
        }
        int d = calcDistance(a,b);
        gameBoard[b.getRow()][b.getCul()] = gameBoard[a.getRow()][a.getCul()];
        gameBoard[b.getRow()][b.getCul()].addDistance(d);
        gameBoard[b.getRow()][b.getCul()].addPlace(b);
        posC[b.getRow()][b.getCul()].addStepped();




        gameBoard[a.getRow()][a.getCul()] = null;
        turn = !turn;
        if (gameBoard[b.getRow()][b.getCul()].getType().equals("♔")) {
            kingP = new Position(b.getRow(), b.getCul());
            isGameFinished();
            return true;
        }
        String eater = gameBoard[b.getRow()][b.getCul()].getType(); //capture check: fix can eat king
        if (a.getRow() > b.getRow())//↑
        {
            if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                    if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() + 1] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();

                    }
                }
            }
            if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                        gameBoard[b.getRow() - 1][b.getCul()] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();
                    }
                }
            }
            if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() - 1] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();

                    }
                }
            }
        } else if (a.getRow() < b.getRow())//↓
        {
            if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                    if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() + 1] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();

                    }
                }
            }
            if (b.getRow() < getBoardSize() - 1 && (gameBoard[b.getRow() + 1][b.getCul()] != null) && (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals(eater))) {
                if ((b.getRow() == getBoardSize() - 2) || (b.getRow() < getBoardSize() - 2 && (gameBoard[b.getRow() + 2][b.getCul()] != null) && (gameBoard[b.getRow() + 2][b.getCul()].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals("♔")) {
                        gameBoard[b.getRow() + 1][b.getCul()] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();

                    }
                }
            }
            if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() - 1] = null;
                        gameBoard[b.getRow()][b.getCul()].addKill();

                    }
                }
            }
        } else {
            if (a.getCul() > b.getCul())//<-
            {
                if (b.getRow() < getBoardSize() - 1 && (gameBoard[b.getRow() + 1][b.getCul()] != null) && (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() == getBoardSize() - 2) || (b.getRow() < getBoardSize() - 2 && (gameBoard[b.getRow() + 2][b.getCul()] != null) && (gameBoard[b.getRow() + 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() + 1][b.getCul()] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
                if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() - 1][b.getCul()] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
                if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                    if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                            gameBoard[b.getRow()][b.getCul() - 1] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
            } else//->
            {
                if (b.getRow() < getBoardSize() - 1 && (gameBoard[b.getRow() + 1][b.getCul()] != null) && (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() == getBoardSize() - 2) || (b.getRow() < getBoardSize() - 2 && (gameBoard[b.getRow() + 2][b.getCul()] != null) && (gameBoard[b.getRow() + 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() + 1][b.getCul()] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
                if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() - 1][b.getCul()] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
                if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                    if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                        if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                            gameBoard[b.getRow()][b.getCul() + 1] = null;
                            gameBoard[b.getRow()][b.getCul()].addKill();

                        }
                    }
                }
            }
        }

        isGameFinished();
        return true;
    }

    public boolean isLegal(Position a, Position b) {
        if (a.equals(b) || gameBoard[a.getRow()][a.getCul()] == null) {
            return false;
        }
        if(a.getRow() != b.getRow()&&a.getCul() != b.getCul()){
            return false;
        }
        if (turn && !(gameBoard[a.getRow()][a.getCul()].getOwner().isPlayerOne()) || !turn && (gameBoard[a.getRow()][a.getCul()].getOwner().isPlayerOne())) {
            return false;
        }
        if (!gameBoard[a.getRow()][a.getCul()].getType().equals("♔") && isCorner(b)) {
            return false;
        }
        if (a.getCul() < b.getCul()) {
            for (int i = a.getCul() + 1; i <= b.getCul(); i++) {
                if (gameBoard[a.getRow()][i] != null) {
                    return false;
                }
            }
        } else if (a.getCul() > b.getCul()) {
            for (int i = b.getCul(); i < a.getCul(); i++) {
                if (gameBoard[a.getRow()][i] != null) {
                    return false;
                }
            }
        } else {
            if (a.getRow() < b.getRow()) {
                for (int i = a.getRow() + 1; i <= b.getRow(); i++) {
                    if (gameBoard[i][a.getCul()] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = b.getRow(); i < a.getRow(); i++) {
                    if (gameBoard[i][a.getCul()] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isCorner(Position b) {
        return (b.getRow() == 0 || b.getRow() == getBoardSize() - 1) && (b.getCul() == 0 || b.getCul() == getBoardSize() - 1);
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return gameBoard[position.getRow()][position.getCul()];
    }

    @Override
    public Player getFirstPlayer() {
        return p1;
    }

    @Override
    public Player getSecondPlayer() {
        return p2;
    }

    @Override
    public boolean isGameFinished() {
        if (((kingP.getCul() == 0) || (kingP.getCul() == getBoardSize() - 1)) && ((kingP.getRow() == getBoardSize() - 1) || (kingP.getRow() == 0))) //Defenders win

        {
            printByMoves();
            printByEaten();
            printPiecesByDistance();
            printSteppedOn();
            p1.addWin();
            return true;
        }

        if (kingP.getCul() == 0 || (kingP.getCul() > 0 && gameBoard[kingP.getRow()][kingP.getCul() - 1] != null && gameBoard[kingP.getRow()][kingP.getCul() - 1].getType().equals("♟")))// Attackers win
        {
            if (kingP.getCul() == getBoardSize() - 1 || (kingP.getCul() < getBoardSize() - 1 && gameBoard[kingP.getRow()][kingP.getCul() + 1] != null && gameBoard[kingP.getRow()][kingP.getCul() + 1].getType().equals("♟"))) {
                if (kingP.getRow() == 0 || (kingP.getRow() > 0 && gameBoard[kingP.getRow() - 1][kingP.getCul()] != null && gameBoard[kingP.getRow() - 1][kingP.getCul()].getType().equals("♟"))) {
                    if (kingP.getRow() == getBoardSize() || (kingP.getRow() < getBoardSize() && gameBoard[kingP.getRow() + 1][kingP.getCul()] != null && gameBoard[kingP.getRow() + 1][kingP.getCul()].getType().equals("♟"))) {
                        p2.addWin();
                        printByMoves();
                        printByEaten();
                        printPiecesByDistance();
                        printSteppedOn();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return turn;
    }

    @Override
    public void reset() {
        gameBoard = new ConcretePiece[this.BOARD_SIZE][this.BOARD_SIZE];
        turn = false;
        kingP = new Position(5, 5);
        int j = 1;
        int k = 20;
        int l = 7;
        for (int i = 3; i < 8; i++) {
            gameBoard[0][i] = new Pawn(p1);
            gameBoard[0][i].setId("A" + l,l);

            l++;
            if (l == 12) {
                l = 14;
            }
            gameBoard[i][0] = new Pawn(p1);
            gameBoard[i][0].setId("A" + j,j);
            j++;
            gameBoard[this.BOARD_SIZE - 1][i] = new Pawn(p1);
            gameBoard[this.BOARD_SIZE - 1][i].setId("A" + l,l);
            l++;
            gameBoard[i][this.BOARD_SIZE - 1] = new Pawn(p1);
            gameBoard[i][this.BOARD_SIZE - 1].setId("A" + k,k);
            k++;
        }
        gameBoard[1][5] = new Pawn(p1);
        gameBoard[1][5].setId("A12",12);

        gameBoard[5][1] = new Pawn(p1);
        gameBoard[5][1].setId("A6",6);

        gameBoard[9][5] = new Pawn(p1);
        gameBoard[9][5].setId("A13",13);

        gameBoard[5][9] = new Pawn(p1);
        gameBoard[5][9].setId("A19",19);


        gameBoard[3][5] = new Pawn(p2);
        gameBoard[3][5].setId("D5",5);

        gameBoard[4][4] = new Pawn(p2);
        gameBoard[4][4].setId("D2",2);

        gameBoard[4][5] = new Pawn(p2);
        gameBoard[4][5].setId("D6",6);

        gameBoard[4][6] = new Pawn(p2);
        gameBoard[4][6].setId("D10",10);

        gameBoard[5][3] = new Pawn(p2);
        gameBoard[5][3].setId("D1",1);

        gameBoard[5][4] = new Pawn(p2);
        gameBoard[5][4].setId("D3",3);

        gameBoard[5][5] = new King(p2);
        gameBoard[5][5].setId("K7",7);

        gameBoard[5][6] = new Pawn(p2);
        gameBoard[5][6].setId("D11",11);

        gameBoard[5][7] = new Pawn(p2);
        gameBoard[5][7].setId("D13",13);

        gameBoard[6][4] = new Pawn(p2);
        gameBoard[6][4].setId("D4",4);

        gameBoard[6][5] = new Pawn(p2);
        gameBoard[6][5].setId("D8",8);

        gameBoard[6][6] = new Pawn(p2);
        gameBoard[6][6].setId("D12",12);

        gameBoard[7][5] = new Pawn(p2);
        gameBoard[7][5].setId("D9",9);
        pieces = new ArrayList<ConcretePiece>();
        posC = new Position[this.BOARD_SIZE][this.BOARD_SIZE];
        for (int i = 0; i < getBoardSize(); i++) {
            for (int d = 0; d < getBoardSize(); d++) {
                posC[i][d] = new Position(i, d);

                if (gameBoard[i][d] != null) {
                    pieces.add(gameBoard[i][d]);
                    gameBoard[i][d].addPlace(new Position(i, d));

                }
            }
        }

    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }


    class SortByMoves implements Comparator<ConcretePiece> {
        public int compare(ConcretePiece a, ConcretePiece b) {
            int cm = Integer.compare(a.getPlaces().size(), b.getPlaces().size());
            if (cm == 0) {
                return Integer.compare(a.getNummericid(), b.getNummericid());
            }
            return cm;
        }
    }
    class SortByEaten implements Comparator<ConcretePiece>{
        public int compare(ConcretePiece a, ConcretePiece b) {
            boolean win = turn;
            int cm = Integer.compare(b.getKillCount(), a.getKillCount());
            if (cm == 0) {
                if (a.getOwner() == b.getOwner()) {
                    return Integer.compare(b.getNummericid(), a.getNummericid());
                }
                else if(a.getOwner().isPlayerOne()==win) {
                    return 1;
                }
                else return -1;
            }
            return cm;
        }
    }
    class SortByDistance implements Comparator<ConcretePiece>{
        public int compare(ConcretePiece a, ConcretePiece b) {
            boolean win = !turn;

            int cm = Integer.compare(a.getDistance(), b.getDistance());
            if (cm == 0) {
                if(Integer.compare(b.getNummericid(), a.getNummericid())==0){
                    if(a.getOwner().isPlayerOne()==win){
                        return 1;
                    }
                    else return -1;
                }
                else return Integer.compare(b.getNummericid(), a.getNummericid());
            }
            return cm;
        }
    }

    class SortByStepped implements Comparator<Position>{
        public int compare(Position a, Position b) {
            int cm = Integer.compare(a.getStepped(), b.getStepped());
            if (cm == 0) {
                if (Integer.compare(b.getCul(), a.getCul()) == 0) {
                    return Integer.compare(b.getRow(), a.getRow());
                } else return Integer.compare(b.getCul(), a.getCul());
            }

            return cm;
        }
    }

    public void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < getBoardSize(); i++) {
            System.out.print(i + "              ");
            for (int j = 0; j < getBoardSize(); j++) {
                if (gameBoard[i][j] == null) {
                    System.out.print("              ");
                } else {
                    System.out.print(gameBoard[i][j].getId() + "              ");
                }
            }
            System.out.println();
        }
    }

    public void printByMoves() {

        boolean currentP =!turn;
        pieces.sort(new SortByMoves());
        for(int i = 0; i <pieces.size(); i++){
            if ((pieces.get(i).getPlaces().size()>1) &&pieces.get(i).getOwner().isPlayerOne()==currentP) {
                System.out.print(pieces.get(i).getId() + ": " + pieces.get(i).places.toString()+"\n");
            }
        }

        for(int i = 0; i <pieces.size(); i++){
            if ((pieces.get(i).getPlaces().size()>1) &&pieces.get(i).getOwner().isPlayerOne()==!currentP) {
                System.out.print(pieces.get(i).getId() + ": " + pieces.get(i).places.toString()+"\n");
            }
        }

        System.out.print("***************************************************************************\n");

    }

    public void printByEaten() {

        pieces.sort(new SortByEaten());
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getKillCount() != 0) {
                System.out.print(pieces.get(i).getId() + ": " + pieces.get(i).getKillCount() + " kills\n");
            }
        }
        System.out.print("***************************************************************************\n");
    }

    public int calcDistance(Position a, Position b){
        return Math.abs(a.getCul()-b.getCul())+Math.abs(a.getRow()-b.getRow());
    }
    public void printPiecesByDistance(){
        pieces.sort(new SortByDistance());
        for(int i = pieces.size()-1; i > 0; i--){
            if(pieces.get(i).getDistance() != 0) {
                System.out.print(pieces.get(i).getId() + ": " + pieces.get(i).getDistance() + " squares\n");
            }
        }
        System.out.print("***************************************************************************\n");
    }

    public void printSteppedOn(){
        ArrayList<Position> steppedOn = new ArrayList<Position>();
        for(int i = 0; i < getBoardSize(); i++){
            for(int j = 0; j < getBoardSize(); j++){
                if(posC[i][j].getStepped() > 1){
                    steppedOn.add(posC[i][j]);
                }
            }
        }
        steppedOn.sort(new SortByStepped());
        for(int i = steppedOn.size()-1; i >=0; i--){
            System.out.print(steppedOn.get(i).toString() + "" + steppedOn.get(i).getStepped() + " pieces\n");
        }
        System.out.print("***************************************************************************\n");
    }
}