public class Computer extends Player {
    private Board b;
    String boardSetup = "➖";

    public Computer(String token) {
        super("Super AI", token);
        this.setB(new Board());
    }

    // Decides computer move
    public int determineMove(int playerMove) {
        int colNum = getNum();

        // Block if three human tokens in a row horizontally.
        for (int x = 0; x < getSize(); x++) {
            colNum = blockIfThreeHor(x);
            if (colNum != getNum()) {
                return colNum;
            }
        }

        // Block if three human tokens in a row vertically.
        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)) {
                colNum = blockIfThreeVert(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }



        //Diagonal blocks: Semi-rushed, works okay
        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)) {
                colNum = blockIfThreeDiag(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }


        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x) && colNum > 4) {
                colNum = blockIfThreeDiagAlt(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }

        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x) && colNum > 4) {
                colNum = blockIfThreeDiagAlt2(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }

        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)  && colNum > 4) {
                colNum = blockIfThreeDiagAlt3(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }

        // Tries to go for wins by following the last placed token
        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)) {
                colNum = dropIfOneVert(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }


        for (int x = 0; x < getSize(); x++) {
            colNum = dropIfOneHor(x);
            if (colNum != getNum()) {
                return colNum;
            }
        }


        // Puts down tokens if there are two in a row.
        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)) {
                colNum = dropIfTwoVert(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }


        for (int x = 0; x < getSize(); x++) {
            colNum = dropIfTwoHor(x);
            if (colNum != getNum()) {
                return colNum;
            }
        }

        // Computer attempts to win if three in a row
        for (int x = 0; x < getSize(); x++) {
            colNum = winIfThreeHor(x);
            if (colNum != getNum()) {
                return colNum;
            }
        }

        for (int x = 0; x < getSize(); x++) {
            if (getB().colHasRoom(x)) {
                colNum = winIfThreeVert(x);
            }
            if (colNum != getNum()) {
                return colNum;
            }
        }

    // remaining Move strategy
        int col = ((int) (Math.random() * 3)) + 1;
        if (col == 1 && playerMove - 1 > -1) {
            return playerMove - 1;
        } else if (col == 1) {
            return playerMove;
        }
        if (col == 2) {
            return playerMove;
        }
        if (col == 3 && playerMove + 1 < getSize()) {
            return playerMove + 1;
        } else if (col == 3) {
            return playerMove;
        }
        return 1;
    }

    //larger functions!!
    // Blocks human
    public int blockIfThreeVert(int colNum) {
        for (int row = 0; row < getSize(); row++) {
            if (getB().getGrid()[row][colNum].equals("♨️")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row + 1][colNum].equals("♨️")) {
                    if (getB().getGrid()[row + 2][colNum].equals("♨️")) {
                        return colNum;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }

    //Diagonal blocks are not perfect, I had difficulty in implementing them the best possible way,
    // but it works decent, yet sporatic at times.
    //down up left right
    public int blockIfThreeDiag(int colNum) {
        for (int row = 0; row < getSize() - 3; row++) {
            if (getB().getGrid()[row][colNum].equals("♨️")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row + 1][colNum + 1].equals("♨️")) {
                    if (getB().getGrid()[row + 2][colNum + 2].equals("♨️")) {
                        return colNum + 3;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }


    // Up down right left
    public int blockIfThreeDiagAlt(int colNum) {
        for (int row = 0; row < getSize() - 3; row++) {
            if (getB().getGrid()[row][colNum].equals("♨️")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row - 1][colNum - 1].equals("♨️")) {
                    if (getB().getGrid()[row - 2][colNum - 2].equals("♨️")) {
                        return colNum - 3;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }

    //down up right left
    public int blockIfThreeDiagAlt2(int colNum) {
        for (int row = 0; row < getSize() - 3; row++) {
            if (getB().getGrid()[row][colNum].equals("♨️")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row + 1][colNum - 1].equals("♨️")) {
                    if (getB().getGrid()[row + 2][colNum - 2].equals("♨️")) {
                        return colNum - 3;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }

    public int blockIfThreeDiagAlt3(int colNum) {
        for (int row = 0; row < getSize() - 3; row++) {
            if (getB().getGrid()[row][colNum].equals("♨️")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row - 1][colNum + 1].equals("♨️")) {
                    if (getB().getGrid()[row - 2][colNum + 2].equals("♨️")) {
                        return colNum + 3;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }


    // blocks horizontally
    public int blockIfThreeHor(int rowNum) {
        for (int col = 0; col < getSize(); col++) {
            if (getB().getGrid()[rowNum][col].equals("♨️")) {
                if (col + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[rowNum][col + 1].equals("♨️")) {
                    if (getB().getGrid()[rowNum][col + 2].equals("♨️")) {
                        if (col + 3 >= 7 || !getB().getGrid()[rowNum][col + 3].equals(boardSetup)) {
                            if (col - 1 <= 0 || !getB().getGrid()[rowNum][col - 1].equals(boardSetup)) {
                                continue;
                            }
                            if (getB().getGrid()[rowNum][col - 1].equals(boardSetup) && rowNum == 7 ||
                                    getB().getGrid()[rowNum][col - 1].equals(boardSetup) &&
                                            !getB().getGrid()[rowNum + 1][col - 1].equals(boardSetup)) {
                                return col - 1;
                            }
                        } else {
                            if (getB().getGrid()[rowNum][col + 3].equals(boardSetup) && rowNum == 7 ||
                                    getB().getGrid()[rowNum][col + 3].equals(boardSetup) &&
                                            !getB().getGrid()[rowNum + 1][col + 3].equals(boardSetup)) {
                                return col + 3;
                            }
                        }
                    }
                }
            }
        }
        return getNum();
    }


    // Methods to win against human.
    public int winIfThreeVert(int colNum) {
        for (int row = 0; row < getSize(); row++) {
            if (getB().getGrid()[row][colNum].equals("\uD83C\uDF00")) {
                if (row + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[row + 1][colNum].equals("\uD83C\uDF00")) {
                    if (getB().getGrid()[row + 2][colNum].equals("\uD83C\uDF00")) {
                        return colNum;
                    } else {
                        return getNum();
                    }
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }

    public int dropIfTwoVert(int colNum) {
        for (int row = 0; row < getSize(); row++) {
            if (getB().getGrid()[row][colNum].equals("\uD83C\uDF00")) {
                if (row > 6) {
                    return getNum();
                } else if (getB().getGrid()[row + 1][colNum].equals("\uD83C\uDF00")) {
                    return colNum;
                } else {
                    return getNum();
                }
            }
        }
        return getNum();
    }

    public int dropIfOneVert(int colNum) {
        for (int row = 0; row < getSize(); row++) {
            if (getB().getGrid()[row][colNum].equals("\uD83C\uDF00")) {
                return colNum;
            } else {
                return getNum();
            }
        }
        return getNum();
    }

    //Wins if three horizontal tokens are in a row
    public int winIfThreeHor(int rowNum) {
        for (int col = 0; col < getSize(); col++) {
            if (getB().getGrid()[rowNum][col].equals("\uD83C\uDF00")) {
                if (col + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[rowNum][col + 1].equals("\uD83C\uDF00")) {
                    if (getB().getGrid()[rowNum][col + 2].equals("\uD83C\uDF00")) {
                        if (col + 3 < 7 && getB().getGrid()[rowNum][col + 3].equals(boardSetup)) {
                            if (getB().getGrid()[rowNum][col + 3].equals(boardSetup) && rowNum == 7 ||
                                    getB().getGrid()[rowNum][col + 3].equals(boardSetup) &&
                                            !getB().getGrid()[rowNum + 1][col + 3].equals(boardSetup)) {
                                return col + 3;
                            }
                        } else {
                            if (col - 1 > 0 && getB().getGrid()[rowNum][col - 1].equals(boardSetup) && (getB().getGrid()[rowNum][col - 1].equals(boardSetup) && rowNum == 7 ||
                                    getB().getGrid()[rowNum][col - 1].equals(boardSetup) &&
                                            !getB().getGrid()[rowNum + 1][col - 1].equals(boardSetup))) {
                                return col - 1;
                            }
                        }
                    }
                }
            }
        }
        return getNum();
    }

    //Drops another token if already two horizontally
    public int dropIfTwoHor(int rowNum) {
        for (int col = 0; col < getSize(); col++) {
            if (getB().getGrid()[rowNum][col].equals("\uD83C\uDF00")) {
                if (col + 1 > 6) {
                    return getNum();
                } else if (getB().getGrid()[rowNum][col + 1].equals("\uD83C\uDF00")) {
                    if (col + 2 >= 7 || !getB().getGrid()[rowNum][col + 2].equals(boardSetup)) {
                        if (col - 1 <= 0 || !getB().getGrid()[rowNum][col - 1].equals(boardSetup) || ((!getB().getGrid()[rowNum][col - 1].equals(boardSetup) || rowNum != 7) &&
                                (!getB().getGrid()[rowNum][col - 1].equals(boardSetup) ||
                                        getB().getGrid()[rowNum + 1][col - 1].equals(boardSetup)))) {
                            continue;
                        }
                        return col - 1;
                    } else {
                        if (getB().getGrid()[rowNum][col + 2].equals(boardSetup) && rowNum == 7 ||
                                getB().getGrid()[rowNum][col + 2].equals(boardSetup) &&
                                        !getB().getGrid()[rowNum + 1][col + 2].equals(boardSetup)) {
                            return col + 2;
                        }
                    }
                }
            }
        }
        return getNum();
    }

    //Drops another token if one is present horizontally
    public int dropIfOneHor(int rowNum) {
        for (int col = 0; col < getSize(); col++) {
            if (getB().getGrid()[rowNum][col].equals("\uD83C\uDF00")) {
                if (col + 1 > 6) {
                    return getNum();
                } else if (col + 2 < 7 && getB().getGrid()[rowNum][col + 1].equals(boardSetup)) {
                    if (getB().getGrid()[rowNum][col + 2].equals(boardSetup) && rowNum == 7 ||
                            getB().getGrid()[rowNum][col + 2].equals(boardSetup) &&
                                    !getB().getGrid()[rowNum + 1][col + 1].equals(boardSetup)) {
                        return col + 2;
                    }
                } else {
                    if (col - 1 > 0 && getB().getGrid()[rowNum][col - 1].equals(boardSetup) && ((getB().getGrid()[rowNum][col - 1].equals(boardSetup) && rowNum == 7) ||
                            (getB().getGrid()[rowNum][col - 1].equals(boardSetup) &&
                                    !getB().getGrid()[rowNum + 1][col - 1].equals(boardSetup)))) {
                        return col - 1;
                    }
                }
            }
        }
        return getNum();
    }

    //getters and setters: method used
    public Board getB() {
        return b;
    }

    public void setB(Board b) {
        this.b = b;
    }

    public int getSize() {
        return 8;
    }

    public int getNum() {
        return -1;
    }
}