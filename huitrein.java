// A class to represent a queen
class Queen {
  // The row and column of the queen
  private int row;
  private int col;

  // A constructor to create a queen with a given row and column
  public Queen(int row, int col) {
    this.row = row;
    this.col = col;
  }

  // A getter method to return the row of the queen
  public int getRow() {
    return row;
  }

  // A getter method to return the column of the queen
  public int getCol() {
    return col;
  }

  // A method to check if the queen is under attack by another queen
 public boolean isUnderAttack(Queen other) {
    // Check if they are in the same row, column, or diagonal
    return this.row == other.row ||    // Added logical OR operator
           this.col == other.col ||
           Math.abs(this.row - other.row) == Math.abs(this.col - other.col);
}

}

// A class to represent a chessboard
class Board {
  // The size of the board
  public static final int SIZE = 8;

  // An array to store the queens on the board
  private Queen[] queens;

  // A constructor to create an empty board
  public Board() {
    queens = new Queen[SIZE];
  }

  // A method to place a queen on the board
  public void placeQueen(Queen queen, int col) {
    queens[col] = queen;
  }

  // A method to remove a queen from the board
  public void removeQueen(int col) {
    queens[col] = null;
  }

  // A method to check if the board is valid, i.e. no queens are under attack
  public boolean isValid() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = i + 1; j < SIZE; j++) {
        if (queens[i] != null && queens[j] != null && queens[i].isUnderAttack(queens[j])) {
          return false;
        }
      }
    }
    return true;
  }

  // A method to display the board
  public void display() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (queens[j] != null && queens[j].getRow() == i) {
          System.out.print("Q ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }
}

// A class to solve the Eight Queens problem using backtracking
class Solver {
  // A method to find a solution to the problem
  public static boolean solve(Board board, int col) {
    // If all columns are filled, the problem is solved
    if (col == Board.SIZE) {
      return true;
    }
    // Try each row in the current column
    for (int row = 0; row < Board.SIZE; row++) {
      // Create a new queen
      Queen queen = new Queen(row, col);
      // Place the queen on the board
      board.placeQueen(queen, col);
      // Check if the board is valid
      if (board.isValid()) {
        // Recursively try the next column
        if (solve(board, col + 1)) {
          return true;
        }
      }
      // Remove the queen from the board
      board.removeQueen(col);
    }
    // No solution found in this column
    return false;
  }

  // A main method to test the solver
  public static void main(String[] args) {
    // Create a new board
    Board board = new Board();
    // Try to find a solution
    if (solve(board, 0)) {
      // Display the solution
      board.display();
    } else {
      // No solution found
      System.out.println("No solution found");
    }
  }
}