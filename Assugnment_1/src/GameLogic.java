import java.util.Comparator;
import java.util.Stack;

public class GameLogic implements PlayableLogic {
    private final int BOARD_SIZE = 11;
    private final ConcretePlayer p1 = new ConcretePlayer(true);
    private final ConcretePlayer p2 = new ConcretePlayer(false);
    private ConcretePiece[][] gameBoard;
    private boolean turn;
    private Position kingP;

    public GameLogic() {
        gameBoard = new ConcretePiece[this.BOARD_SIZE][this.BOARD_SIZE];
        turn = true;
        kingP = new Position(5, 5);
        int j = 1;
        int k = 20;
        int l = 7;
        for (int i = 3; i < 8; i++) {
            gameBoard[0][i] = new Pawn(p1);
            gameBoard[0][i].setId("A" + l);

            l++;
            if (l == 12) {
                l = 14;
            }
            gameBoard[i][0] = new Pawn(p1);
            gameBoard[i][0].setId("A" + j);
            j++;
            gameBoard[this.BOARD_SIZE - 1][i] = new Pawn(p1);
            gameBoard[this.BOARD_SIZE - 1][i].setId("A" + l);
            l++;
            gameBoard[i][this.BOARD_SIZE - 1] = new Pawn(p1);
            gameBoard[i][this.BOARD_SIZE - 1].setId("A" + k);
            k++;
        }
        gameBoard[1][5] = new Pawn(p1);
        gameBoard[1][5].setId("A12");

        gameBoard[5][1] = new Pawn(p1);
        gameBoard[1][5].setId("A6");

        gameBoard[9][5] = new Pawn(p1);
        gameBoard[1][5].setId("A13");

        gameBoard[5][9] = new Pawn(p1);
        gameBoard[1][5].setId("A19");


        gameBoard[3][5] = new Pawn(p2);
        gameBoard[1][5].setId("D5");

        gameBoard[4][4] = new Pawn(p2);
        gameBoard[1][5].setId("D2");

        gameBoard[4][5] = new Pawn(p2);
        gameBoard[1][5].setId("D6");

        gameBoard[4][6] = new Pawn(p2);
        gameBoard[1][5].setId("D10");

        gameBoard[5][3] = new Pawn(p2);
        gameBoard[1][5].setId("D1");

        gameBoard[5][4] = new Pawn(p2);
        gameBoard[1][5].setId("D3");

        gameBoard[5][5] = new King(p2);
        gameBoard[1][5].setId("D7");

        gameBoard[5][6] = new Pawn(p2);
        gameBoard[1][5].setId("D11");

        gameBoard[5][7] = new Pawn(p2);
        gameBoard[1][5].setId("D13");

        gameBoard[6][4] = new Pawn(p2);
        gameBoard[1][5].setId("D4");

        gameBoard[6][5] = new Pawn(p2);
        gameBoard[1][5].setId("D8");

        gameBoard[6][6] = new Pawn(p2);
        gameBoard[1][5].setId("D12");

        gameBoard[7][5] = new Pawn(p2);
        gameBoard[1][5].setId("D9");

    }

    @Override
    public boolean move(Position a, Position b) {
        if (!isLegal(a, b)) {
            return false;
        }
        gameBoard[b.getRow()][b.getCul()] = gameBoard[a.getRow()][a.getCul()];
        gameBoard[a.getRow()][a.getCul()] = null;
        turn = !turn;
        if (gameBoard[b.getRow()][b.getCul()].getType().equals("♔")) {
            kingP = new Position(b.getRow(), b.getCul());
            return true;
        }
        String eater = gameBoard[b.getRow()][b.getCul()].getType(); //capture check: fix can eat king
        if (a.getRow() > b.getRow())//↑
        {
            if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                    if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() + 1] = null;

                    }
                }
            }
            if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                        gameBoard[b.getRow() - 1][b.getCul()] = null;

                    }
                }
            }
            if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() - 1] = null;

                    }
                }
            }
        } else if (a.getRow() < b.getRow())//↓
        {
            if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                    if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() + 1] = null;

                    }
                }
            }
            if (b.getRow() < getBoardSize() - 1 && (gameBoard[b.getRow() + 1][b.getCul()] != null) && (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals(eater))) {
                if ((b.getRow() == getBoardSize() - 2) || (b.getRow() < getBoardSize() - 2 && (gameBoard[b.getRow() + 2][b.getCul()] != null) && (gameBoard[b.getRow() + 2][b.getCul()].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals("♔")) {
                        gameBoard[b.getRow() + 1][b.getCul()] = null;

                    }
                }
            }
            if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                    if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                        gameBoard[b.getRow()][b.getCul() - 1] = null;

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

                        }
                    }
                }
                if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() - 1][b.getCul()] = null;

                        }
                    }
                }
                if (b.getCul() > 0 && (gameBoard[b.getRow()][b.getCul() - 1] != null) && (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals(eater))) {
                    if ((b.getCul() - 1 == 0) || (b.getCul() > 1 && (gameBoard[b.getRow()][b.getCul() - 2] != null) && (gameBoard[b.getRow()][b.getCul() - 2].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow()][b.getCul() - 1].getType().equals("♔")) {
                            gameBoard[b.getRow()][b.getCul() - 1] = null;

                        }
                    }
                }
            } else//->
            {
                if (b.getRow() < getBoardSize() - 1 && (gameBoard[b.getRow() + 1][b.getCul()] != null) && (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() == getBoardSize() - 2) || (b.getRow() < getBoardSize() - 2 && (gameBoard[b.getRow() + 2][b.getCul()] != null) && (gameBoard[b.getRow() + 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() + 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() + 1][b.getCul()] = null;

                        }
                    }
                }
                if (b.getRow() > 0 && (gameBoard[b.getRow() - 1][b.getCul()] != null) && (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals(eater))) {
                    if ((b.getRow() - 1 == 0) || (b.getRow() > 1 && (gameBoard[b.getRow() - 2][b.getCul()] != null) && (gameBoard[b.getRow() - 2][b.getCul()].getType().equals(eater)))) {
                        if (!gameBoard[b.getRow() - 1][b.getCul()].getType().equals("♔")) {
                            gameBoard[b.getRow() - 1][b.getCul()] = null;

                        }
                    }
                }
                if (b.getCul() < getBoardSize() - 1 && (gameBoard[b.getRow()][b.getCul() + 1] != null) && (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals(eater))) {
                    if ((b.getCul() == getBoardSize() - 2) || (b.getCul() < getBoardSize() - 2 && ((gameBoard[b.getRow()][b.getCul() + 2] != null) && (gameBoard[b.getRow()][b.getCul() + 2].getType().equals(eater))))) {
                        if (!gameBoard[b.getRow()][b.getCul() + 1].getType().equals("♔")) {
                            gameBoard[b.getRow()][b.getCul() + 1] = null;

                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isLegal(Position a, Position b) {
        if (a.equals(b) || gameBoard[a.getRow()][a.getCul()] == null) {
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
            return true;
        }

        if (kingP.getCul() == 0 || (kingP.getCul() > 0 && gameBoard[kingP.getRow()][kingP.getCul() - 1] != null && gameBoard[kingP.getRow()][kingP.getCul() - 1].getType().equals("♟")))// Attackers win
        {
            if (kingP.getCul() == getBoardSize() - 1 || (kingP.getCul() < getBoardSize() - 1 && gameBoard[kingP.getRow()][kingP.getCul() + 1] != null && gameBoard[kingP.getRow()][kingP.getCul() + 1].getType().equals("♟"))) {
                if (kingP.getRow() == 0 || (kingP.getRow() > 0 && gameBoard[kingP.getRow() - 1][kingP.getCul()] != null && gameBoard[kingP.getRow() - 1][kingP.getCul()].getType().equals("♟"))) {
                    if (kingP.getRow() == getBoardSize() || (kingP.getRow() < getBoardSize() && gameBoard[kingP.getRow() + 1][kingP.getCul()] != null && gameBoard[kingP.getRow() + 1][kingP.getCul()].getType().equals("♟"))) {
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
        turn = true;
        kingP = new Position(5, 5);
        int j = 1;
        int k = 20;
        int l = 7;
        for (int i = 3; i < 8; i++) {
            gameBoard[0][i] = new Pawn(p1);
            gameBoard[0][i].setId("A" + l);

            l++;
            if (l == 12) {
                l = 14;
            }
            gameBoard[i][0] = new Pawn(p1);
            gameBoard[i][0].setId("A" + j);
            j++;
            gameBoard[this.BOARD_SIZE - 1][i] = new Pawn(p1);
            gameBoard[this.BOARD_SIZE - 1][i].setId("A" + l);
            l++;
            gameBoard[i][this.BOARD_SIZE - 1] = new Pawn(p1);
            gameBoard[i][this.BOARD_SIZE - 1].setId("A" + k);
            k++;
        }
        gameBoard[1][5] = new Pawn(p1);
        gameBoard[1][5].setId("A12");

        gameBoard[5][1] = new Pawn(p1);
        gameBoard[1][5].setId("A6");

        gameBoard[9][5] = new Pawn(p1);
        gameBoard[1][5].setId("A13");

        gameBoard[5][9] = new Pawn(p1);
        gameBoard[1][5].setId("A19");


        gameBoard[3][5] = new Pawn(p2);
        gameBoard[1][5].setId("D5");

        gameBoard[4][4] = new Pawn(p2);
        gameBoard[1][5].setId("D2");

        gameBoard[4][5] = new Pawn(p2);
        gameBoard[1][5].setId("D6");

        gameBoard[4][6] = new Pawn(p2);
        gameBoard[1][5].setId("D10");

        gameBoard[5][3] = new Pawn(p2);
        gameBoard[1][5].setId("D1");

        gameBoard[5][4] = new Pawn(p2);
        gameBoard[1][5].setId("D3");

        gameBoard[5][5] = new King(p2);
        gameBoard[1][5].setId("D7");

        gameBoard[5][6] = new Pawn(p2);
        gameBoard[1][5].setId("D11");

        gameBoard[5][7] = new Pawn(p2);
        gameBoard[1][5].setId("D13");

        gameBoard[6][4] = new Pawn(p2);
        gameBoard[1][5].setId("D4");

        gameBoard[6][5] = new Pawn(p2);
        gameBoard[1][5].setId("D8");

        gameBoard[6][6] = new Pawn(p2);
        gameBoard[1][5].setId("D12");

        gameBoard[7][5] = new Pawn(p2);
        gameBoard[1][5].setId("D9");

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

            return a.places.size() - b.places.size();
        }
    }
    class SortByEaten implements Comparator<ConcretePiece>{
        public int compare(ConcretePiece a, ConcretePiece b) {

            return a.getKillCount()-b.getKillCount();
        }
    }
}