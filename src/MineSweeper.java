import java.util.Scanner;

public class MineSweeper {
    String Board[][];
    String MinedBoard[][];
    int line = 0, column = 0;

    boolean gamecontrol = true;

    Scanner scanner = new Scanner(System.in);

    public void Run() {


        System.out.printf("Lütfen Satır Giriniz:");
        line = scanner.nextInt();
        System.out.printf("Lütfen Sütun Giriniz:");
        column = scanner.nextInt();

        if (line > 0 && column > 0) {

            Board = new String[line][column];
            MinedBoard = new String[line][column];


            for (int i = 0; i < line; i++) {
                for (int j = 0; j < column; j++) {
                    Board[i][j] = "-";
                    MinedBoard[i][j] = "-";
                }
            }

        } else {
            System.out.println("Lütfen 0'dan büyük rakamlar giriniz!!");
            System.out.printf("Lütfen Satır Giriniz:");
            line = scanner.nextInt();
            System.out.printf("Lütfen Sütun Giriniz:");
            column = scanner.nextInt();
        }

        ShowBoard(Board);
        System.out.println("----------------------------------------");
        addMine(MinedBoard);
        Answer(MinedBoard);

    }


    public void addMine(String arr[][]) {
        int mine = (line * column) / 4; //
        for (int i = 0; i < mine; i++) {
            int randLine = (int) (Math.random() * line);
            int randColumn = (int) (Math.random() * column);

            while (arr[randLine][randColumn].equals("*")) {
                randLine = (int) (Math.random() * line);
                randColumn = (int) (Math.random() * column);
            }

            arr[randLine][randColumn] = "*";
        }
    }

    public int countMind(int lines1, int columns1) {
        int point = 0;
        for (int i = -1; i <= +1; i++) {
            for (int j = -1; j <= +1; j++) {
                if (lines1 + i >= 0 && lines1 + i < line && columns1 + j >= 0 && columns1 + j < column) {
                    if (MinedBoard[lines1 + i][columns1 + j].equals("*")) {
                        point++;
                    }
                }
            }
        }
        return point;
    }

    public void checkWin() {
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if (MinedBoard[i][j].equals("-")) {
                    return;
                }
            }
        }
        ShowBoard(MinedBoard);
        System.out.println("Tebrikler Oyunu Kazandınız.");
        gamecontrol = false;
    }

    public void Answer(String arr[][]) {
        int line1 = 0, column1 = 0;
        while (gamecontrol) {


            System.out.println("Lütfen Seçmek istediğiniz Satırı Giriniz:");
            line1 = scanner.nextInt();
            System.out.println("Lütfen Seçmek istediğiniz Sütunu Giriniz:");
            column1 = scanner.nextInt();


            if (line1 >= 0 && line1 < arr.length && column1 >= 0 && column1 < arr[line1].length) {

                if (arr[line1][column1].equals("*")) {
                    gamecontrol = false;
                    System.out.println("Game Over");
                    ShowBoard(MinedBoard);
                } else {
                    int point = countMind(line1, column1);
                    Board[line1][column1] = String.valueOf(point);
                    MinedBoard[line1][column1] = String.valueOf(point);
                    checkWin();
                    if (gamecontrol != false) {
                        ShowBoard(Board);
                    }

                }

            } else {
                System.out.println("Lütfen geçersiz bir değer girmeyiniz!");
            }


        }

    }


    public void ShowBoard(String dizi[][]) {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dizi[i][j] + " ");
            }
            System.out.println();
        }
    }

}
