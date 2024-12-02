import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private String[][] board;
    private String[][] minedBoard;
    private int rowSize, columnSize;
    private boolean isGameInProgress;
    private Scanner scanner;

    public void initializeVariables() {
        scanner = new Scanner(System.in);
        isGameInProgress = true;
    }

    public void initializeGame() {
        initializeVariables();
        board = createBoard();
        minedBoard = Arrays.stream(board)
                .map(String[]::clone)
                .toArray(String[][]::new);

        showBoard(board);
        System.out.println("----------------------------------------");
        addMine(minedBoard);
        startGame();
    }

    public String[][] createBoard() {
        String[][] board;
         do {
             System.out.print("Lütfen Satır Giriniz:");
             rowSize = scanner.nextInt();
             System.out.print("Lütfen Sütun Giriniz:");
             columnSize = scanner.nextInt();

             if (rowSize <= 0 || columnSize <= 0) {
                 System.out.println("Lütfen 0'dan büyük rakamlar giriniz!!");
             } else{
                 break;
             }
         } while (true);

        board = new String[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                board[i][j] = "-";
            }
        }
        return board;
    }




    public void addMine(String[][] arr) {
        int mine = (rowSize * columnSize) / 4; //
        Random random = new Random();
        int randRow;
        int randColumn;

        for (int i = 0; i < mine; i++) {
            do {
                randRow = random.nextInt(rowSize);
                randColumn = random.nextInt(columnSize);
            } while (arr[randRow][randColumn].equals("*"));

            arr[randRow][randColumn] = "*";
        }
    }

    public int countMine(int row, int column) {
        int point = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i < rowSize && column + j >= 0 && column + j < columnSize) {
                    if (minedBoard[row + i][column + j].equals("*")) {
                        point++;
                    }
                }
            }
        }
        return point;
    }

    public void checkWin() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (minedBoard[i][j].equals("-")) {
                    return;
                }
            }
        }
        showBoard(minedBoard);
        System.out.println("Tebrikler Oyunu Kazandınız.");
        isGameInProgress = false;
    }

    public void startGame() {
        int selectedRow, selectedColumn;
        while (isGameInProgress) {
            System.out.println("Lütfen Seçmek istediğiniz Satırı Giriniz:");
            selectedRow = scanner.nextInt();
            System.out.println("Lütfen Seçmek istediğiniz Sütunu Giriniz:");
            selectedColumn = scanner.nextInt();

            if ((selectedRow >= 0 && minedBoard.length > selectedRow  ) && (selectedColumn >= 0 && minedBoard[selectedRow].length > selectedColumn )) {
                if (minedBoard[selectedRow][selectedColumn].equals("*")) {
                    isGameInProgress = false;
                    System.out.println("Game Over");
                    showBoard(this.minedBoard);
                } else {
                    int point = countMine(selectedRow, selectedColumn);
                    board[selectedRow][selectedColumn] = String.valueOf(point);
                    this.minedBoard[selectedRow][selectedColumn] = String.valueOf(point);
                    checkWin();
                    if (isGameInProgress) {
                        showBoard(board);
                    }
                }
            } else {
                System.out.println("Lütfen geçersiz bir değer girmeyiniz!");
            }
        }
    }


    public void showBoard(String[][] dizi) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(dizi[i][j] + " ");
            }
            System.out.println();
        }
    }

}
