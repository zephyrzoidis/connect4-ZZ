//DONE
public class Board {
    private int size = 8;
    private String[][] grid;
    String boardSetup = "➖";

    // 8x8 board
    public Board() {
        this.grid = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = boardSetup;
            }
        }
    }

    public String[][] getGrid() {
        return this.grid;
    }

    // Displays the board.
    public void showBoard() {
        System.out.println("Updated Board:");
        for (int rows = 0; rows < size; rows++) {
            for (int cols = 0; cols < size; cols++) {
                System.out.print(grid[rows][cols] + " ");
            }
            System.out.print("\n");
        }
    }

    // Places a token in an empty spot.
    public void makeMove(int col, String token) {
        for (int row = size - 1; row >= 0; row--) {
            if (grid[row][col].equals(boardSetup)) {
                grid[row][col] = token;
                break;
            }
        }
    }

    // Checks to see if column is not full.
    public boolean colHasRoom(int col) {
        return grid[0][col].equals(boardSetup);
    }

    public boolean checkWin(String token) {
        boolean v = checkWinVert(token);
        boolean h = checkWinHor(token);
        boolean d = checkWinDiag(token);
        return v || h || d;
    }

    public boolean checkWinVert(String token) {
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size - 3; row++) {
                if (grid[row][col].equals(token) &&
                        grid[row + 1][col].equals(token) &&
                        grid[row + 2][col].equals(token) &&
                        grid[row + 3][col].equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinHor(String token) {
        for (int col = 0; col < size - 3; col++) {
            for (int row = 0; row < size; row++) {
                if (grid[row][col].equals(token) &&
                        grid[row][col + 1].equals(token) &&
                        grid[row][col + 2].equals(token) &&
                        grid[row][col + 3].equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkWinDiag(String token){
        //right
        for(int row = 7;row > 2;row--){
            for(int col = 0;col < size-3;col++){
                if(grid[row][col].equals(token) &&
                        grid[row-1][col+1].equals(token) &&
                        grid[row-2][col+2].equals(token) &&
                        grid[row-3][col+3].equals(token)){
                    return true;
                }
            }
        }
        //left
        for(int row = 7;row > 2;row--){
            for(int col = 3;col < size;col++){
                if(grid[row][col].equals(token) &&
                        grid[row-1][col-1].equals(token) &&
                        grid[row-2][col-2].equals(token) &&
                        grid[row-3][col-3].equals(token)){
                    return true;
                }
            }
        }
        return false;
    }
}