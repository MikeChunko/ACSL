
import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;

public class Lights_Out {
    public static void main(String[] args) {

        //'original' represents the state of the board before the tile has been pressed
        //'board' represents the board after a tile has been pressed
        boolean[][] original = null;
        boolean[][] board = new boolean[8][8];

        try {
            //Reads in each hexidecimal line
            for (String line : Files.readAllLines(Paths.get("D:/Mike/Actual Documents/ACSL.txt"))) {

                //Converts the hexadecimal to binary
                line = line.replaceAll(" ", "");
                line = new BigInteger(line, 16).toString(2);

                //Pads '0's onto the front of the binary string until it is 64 characters long
                while (line.length() < 64)
                    line = "0" + line;


                //'i' represents width and 'j' represents height
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (line.charAt(i + (8 * (7 - j))) == '1')
                            board[i][j] = true;
                        else
                            board[i][j] = false;
                    }
                }

                //Finds the first difference between the two boards
                if (original != null) {
                    findChanged(board, original);
                }

                //Clones the current board to 'original'
                original = new boolean[8][8];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        original[i][j] = board[i][j];
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Prints a 2D boolean array using '1's and '0's
    public static void printBoard(boolean[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == true)
                    System.out.print(1);
                else
                    System.out.print(0);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void findChanged(boolean[][] board, boolean[][] original) {
        int differentRow = -1, differentColumn = -1;

        for (int i = 0; i < 8 && differentRow == -1; i++) {
            for (int j = 0; j < 8 && differentColumn == -1; j++) {
                if (board[j][i] != original[j][i]) {
                    differentRow = j;
                    differentColumn = i;
                }
            }
        }

        boolean flag = false;

        if (checkAdjacent(differentRow, differentColumn, board, original) == false) {
            if (checkAdjacent(differentRow + 1, differentColumn, board, original) == true)
                differentRow++;
            else {
                if (checkAdjacent(differentRow - 1, differentColumn, board, original) == true)
                    differentRow--;
                else {
                    if (checkAdjacent(differentRow, differentColumn + 1, board, original) == true)
                        differentColumn++;
                    else {
                        if (checkAdjacent(differentRow, differentColumn - 1, board, original) == true)
                            differentColumn--;
                        else
                            flag = true;
                    }
                }
            }
        }

        if (flag == false) {
            if (checkAdjacent(differentRow + 1, differentColumn, board, original) == true)
                differentRow++;
            else {
                if (checkAdjacent(differentRow - 1, differentColumn, board, original) == true)
                    differentRow--;
                else {
                    if (checkAdjacent(differentRow, differentColumn + 1, board, original) == true)
                        differentColumn++;
                    else {
                        if (checkAdjacent(differentRow, differentColumn - 1, board, original) == true)
                            differentColumn--;
                    }
                }
            }
        }

        System.out.println("" + (9 - (differentColumn + 1)) + (differentRow + 1));
    }

    public static boolean checkAdjacent(int row, int column, boolean[][] board, boolean[][] original) {
        boolean flag = true;

        if (row + 1 < 8 && row + 1 >= 0 && column >= 0 && column < 8 && board[row + 1][column] == original[row + 1][column])
            flag = false;
        else {
            if (row < 8 && row >= 0 && column + 1 >= 0 && column + 1 < 8 && board[row][column + 1] == original[row][column + 1])
                flag = false;
            else {
                if (row - 1 < 8 && row - 1 >= 0 && column >= 0 && column < 8 && board[row - 1][column] == original[row - 1][column])
                    flag = false;
                else {
                    if (row < 8 && row >= 0 && column - 1 >= 0 && column - 1 < 8 && board[row][column - 1] == original[row][column - 1])
                        flag = false;
                }
            }
        }

        return flag;
    }
}