public class Main {
  public static int[][] createBoard(int rows, int cols) {
    return new int[rows][cols];
  }

  public static void printBoard(int[][] board) {
    final char BLACK_SQUARE = '⬛';
    final char WHITE_SQUARE = '⬜';

    for (int i = 0; i < board.length; i++) {
      System.out.println();
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 0) {
          System.out.print(BLACK_SQUARE);
        } else {
          System.out.print(WHITE_SQUARE);
        }
      }
    }
  }

  public static int[][] updateBoard(int[][] board) {
    int[][] newBoard = new int[board.length][board[0].length];

    for (int i = 1; i < board.length-1; i++) {
      for (int j = 1; j < board[i].length-1; j++) {
        int neighbourCount = countNeighbors(board, i, j);

        if ( (board[i][j] == 1 && (neighbourCount == 2 || neighbourCount == 3)) || (board[i][j] == 0 && neighbourCount == 3) ) {
          newBoard[i][j] = 1;
        } else {
          newBoard[i][j] = 0;
        }
      }
    }

    return newBoard;
  }

  public static int countNeighbors(int[][] board, int row, int col) {
    int count = 0;

    for (int i = row-1; i <= row + 1; i++) {
      for (int j = col-1; j <= col + 1; j++) {
        if (board[i][j] == 1 && !(i == row && j == col)) {
          count++;
        }
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[][] board = createBoard(50, 50);

    board[1][3] = 1;
    board[2][1] = 1;
    board[2][3] = 1;
    board[3][2] = 1;
    board[3][3] = 1;

    printBoard(board);

    for (int i = 0; i < 200; i++) {
      System.out.println();

      board = updateBoard(board);
      printBoard(board);
    }
  }
}