import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bingo
{

    ArrayList<Board> someBoards;

    public Bingo()
    {
        someBoards = new ArrayList<>();
        
    }

    public void createBoards(File boardFile) throws FileNotFoundException
    {
        Scanner boardScanner = new Scanner(boardFile);

        while (boardScanner.hasNext())
        {
            String aLine = boardScanner.nextLine();
            String mew = "";
        }

    }

    public void parseBingoNumbers(File numbersFile) throws FileNotFoundException
    {
        Scanner boardScanner = new Scanner(numbersFile);
        boardScanner.useDelimiter(",");

        while (boardScanner.hasNext())
        {
            String aLine = boardScanner.next();
            String mew = "";
        }

    }

}

class BingoTesters
{
    public static void main(String[] sdasdasd) throws FileNotFoundException
    {
        Bingo thatsABingo = new Bingo();
        thatsABingo.createBoards(new File("bingo_boards"));
        //thatsABingo.parseBingoNumbers(new File("bingo_numbers.txt"));

    }
}


class Board
{
    Piece[][] aBoard;
    Piece[] piecesArray;
    int rows;
    int columns;
    HashMap<String, Integer> rowsChosen; //Used for determining how many slots in each row is chosen
    HashMap<String, Integer> columnsChosen;

    public Board(int aRows, int aColumns)
    {
        aBoard = new Piece[aRows][aColumns];
        this.rows = aRows;
        this.columns = aColumns;
        this.rowsChosen = new HashMap<>();
        this.columnsChosen = new HashMap<>();
        createHashMaps(this.rows, this.columns);
        piecesArray = new Piece[this.rows * this.columns];
        Arrays.sort(piecesArray);
        //Arrays.binarySearch(piecesArray, )
    }

    private void createHashMaps(int rows, int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            String rowLocation = Integer.toString(i);
            rowsChosen.put(rowLocation, 0);
        }

        for (int i = 0; i < columns; i++)
        {
            String columnLocation = Integer.toString(i);
            columnsChosen.put(columnLocation, 0);
        }
    }

    public boolean insertPiece(int row, int column, int value)
    {
        aBoard[row][column] = new Piece(row, column, value);

        return true;
    }

    public void selectPiece(int value)
    {

    }


}

class Piece implements Comparable
{
    int x, y, value;
    boolean chosen;

    public Piece(int aX, int aY, int aValue)
    {
        this.x = aX;
        this.y = aY;
        this.value = aValue;
        this.chosen = false;
    }

    public void choosePiece()
    {
        this.chosen = true;
    }

    @Override
    public int compareTo(Object o)
    {
        //o = (Piece) o;

        if (this.value == ((Piece) o).value)
        {
            return 0;
        } else if (this.value > ((Piece) o).value)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return  value == piece.value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, value, chosen);
    }
}