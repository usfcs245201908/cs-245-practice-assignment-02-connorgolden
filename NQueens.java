public class NQueens {

    private int boardsize;
    private int[][] board;

    NQueens(int size){
        boardsize = size;
        board = new int[boardsize][boardsize];
    }

    boolean placeNQueens(){

        if (boardsize<=0){
            // Throw Exception HERE, not in the constructor!
            // Does not let you make a board with 0 or negative dimensions.
            throw new IllegalArgumentException("Cannot have a negative size!");
        }
        //Start Recursive loop,
        return recursivePlaceQueen(0);
    }

    private boolean recursivePlaceQueen(int row){
        //Boolean to keep track if the placements are good.
        boolean queensPlaced = false;

        if(row>=boardsize){
            // If we get to the end of the board recursively and all is well, then return true through the recursion.
            return true;
        }

        for (int col = 0; col < boardsize; col++) {
            //Loop through positions on the row.

            if(check_safe(row, col)){
                // If the position is safe, TRY this position and continue recursion for the next row.
                // If the continued recursion fails, we will remove this queen and try the next position on the row.
                board[row][col] = 1;
                queensPlaced = recursivePlaceQueen(row+1);
            }

            if(!queensPlaced){
                //if the placement of the first queen on the row does not work out,
                // then reset that spot to 0 (remove queen), and let the loop try the next spot on row.
                board[row][col] = 0;
            }else{
                // if the placement of the queen is correct we can break the loop where it is, and return.
                break;
            }
        }
        return queensPlaced;
    }

    private boolean check_safe(int row, int col){

        // Because we are iterating with rows, we need to check the columns and diagonals. We should never have two in
        // the same row. Because we are starting at row 0, and go up, we only need to check in the backward direction.
        // (counting down)

        //Storing the variables this way to better understand the loops below
        int prevrow = row-1;
        int prevcol = col-1;
        int nextcol = col+1;

        //Check Column (count down)
        for (int r = prevrow; r >= 0; r--) {
            if(board[r][col] == 1){
                return false;
            }
        }

        // Check diagionals (counting down)
        //Left
        for (int r = prevrow, c = prevcol; r >= 0 && c >= 0; r--, c--){
            if (board[r][c] == 1){
                return false;
            }
        }

        //Right
        for (int r = prevrow, c = nextcol; r >= 0 && c < boardsize; r--, c++) {
            if(board[r][c] == 1){
                return false;
            }
        }

        //If we get this far, then no queens previously placed can attack the spot in question.
        return true;
    }

    private void printToConsole(){

        System.out.println();
        //Simple loop through rows, then columns. If the array has value 1, print Q, if it has 0, print underscore.
        for (int row = boardsize-1; row >= 0; row--) {
            // inverted row counting, so row 0 is at bottom, just for my brain's sake.
            for (int col = 0; col < board.length; col++) {
                // normal column counting, meaning [0][0] is the bottom left slot.
                if(board[row][col] == 1){
                    System.out.print("Q");
                }else if(board[row][col] == 0){
                    System.out.print("_");
                }
                System.out.print(" ");
            }
            // Print newline
            System.out.print("\n");
        }
    }

    public static void main(String [] args){
        //Main For My own testing, please disregard.

        int[] toTest = {1,6,8,18,2,3};

        for (int num: toTest) {
            NQueens nQueens = new NQueens(num);

            if (nQueens.placeNQueens()){
                System.out.println(""+num+"x"+num+" Board and "+num+" Queens Works");
            } else {
                System.out.println(""+num+"x"+num+" Board and "+num+" Queens Does NOT Work");
            }

            System.out.println("Board Printout:");
            nQueens.printToConsole();
            System.out.println("\n-----------------------------------------------");
        }
    }
}