import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bingo implements Iterable
{

    ArrayList<Board> someBoards;

    public Bingo()
    {
        someBoards = new ArrayList<>();

    }

    public void createBoards(File boardFile) throws FileNotFoundException
    {
        Scanner boardScanner = new Scanner(boardFile);
        boolean createNew = false;
        int currentBoardId = 0;
        Board currentBoard = new Board(5, 5, currentBoardId);
        int row = 0, column = 0;

        while (boardScanner.hasNext())
        {
            //TODO Parse each board data file individually
            String aLine = boardScanner.nextLine();

            if (!aLine.equals(""))
            {
                Scanner scanLine = new Scanner(aLine);
                while (scanLine.hasNext())
                {
                    int currentValue = scanLine.nextInt();
                    //currentBoard.
                    currentBoard.insertPiece(row, column, currentValue);
                    column += 1;
                }

                if (row == 4)
                {
                    someBoards.add(currentBoard);
                    Arrays.sort(currentBoard.piecesArray);
                    currentBoardId++;
                    currentBoard = new Board(5, 5, currentBoardId);
                    row = 0;
                } else
                {
                    row += 1;
                }
                column = 0;
            }

            //String mew = "";
        }

    }

    public int parseBingoNumbers(File numbersFile) throws FileNotFoundException
    {
        if (someBoards.size() == 0)
        {
            throw new IllegalArgumentException("Must create boards to parse through Bingo numbers first");
        }
        Scanner boardScanner = new Scanner(numbersFile);
        boardScanner.useDelimiter(",");

        while (boardScanner.hasNext())
        {
            String aValue = boardScanner.next();
            //int aBingoValue = boardScanner.nextInt();
            int aBingoValue = Integer.parseInt(aValue);
            Iterator<Board> boardIterator = iterator();
            boolean foundPiece = false;

            while (boardIterator.hasNext())
            {
                Board aBoard = boardIterator.next();
                aBoard.selectPiece(aBingoValue);
                if (aBoard.hasBingo)
                {
                    return aBoard.id;
                }
            }

            String mew = "";
        }
        return -1;
    }

    @Override
    public Iterator iterator()
    {
        final int[] current = {0};
        Iterator<Board> someIterator = new Iterator<Board>()
        {
            @Override
            public boolean hasNext()
            {
                return current[0] < someBoards.size();
                //return someBoards.get(current[0]) != null;
            }

            @Override
            public Board next()
            {
                Board aBoard = someBoards.get(current[0]);
                current[0]++;
                return aBoard;
            }
        };
        return someIterator;
    }
}

class BingoTesters
{
    public static void main(String[] sdasdasd) throws FileNotFoundException
    {
        Bingo thatsABingo = new Bingo();
        //thatsABingo.createBoards(new File("bingo_small.txt"));
        thatsABingo.createBoards(new File("bingo_boards"));

        int result = thatsABingo.parseBingoNumbers(new File("bingo_numbers.txt"));
        Board aBoard = thatsABingo.someBoards.get(0);
        //Piece somePiece = new Piece(0, 0, 1);
        //int result = Arrays.binarySearch(aBoard.piecesArray, somePiece);
        //int temp = 0;
        //thatsABingo.parseBingoNumbers(new File("bingo_numbers.txt"));

    }
}


class Board
{
    //TODO Create function calculate all un-chosen values in board
    int id;
    Piece[][] aBoard;
    Piece[] piecesArray;
    boolean hasBingo;
    int rows;
    int columns;
    int size;
    HashMap<String, Integer> rowsChosen; //Used for determining how many slots in each row is chosen
    HashMap<String, Integer> columnsChosen;

    public Board(int aRows, int aColumns, int anId)
    {
        aBoard = new Piece[aRows][aColumns];
        this.rows = aRows;
        this.columns = aColumns;
        this.rowsChosen = new HashMap<>();
        this.columnsChosen = new HashMap<>();
        createHashMaps(this.rows, this.columns);
        piecesArray = new Piece[this.rows * this.columns];
        this.size = 0;
        hasBingo = false;
        id = anId;
        //Arrays.sort(piecesArray);
        //Arrays.binarySearch(piecesArray, )

    }

    private void createHashMaps(int rows, int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            String rowLocation = Integer.toString(i);
            rowsChosen.put(rowLocation, null);
        }

        for (int i = 0; i < columns; i++)
        {
            String columnLocation = Integer.toString(i);
            columnsChosen.put(columnLocation, null);
        }
    }

    public boolean isHasBingo()
    {
        return hasBingo;
    }

    public boolean insertPiece(int row, int column, int value)
    {
        Piece aPiece = new Piece(row, column, value);
        aBoard[row][column] = aPiece;
        piecesArray[size] = aPiece;

        size++;
        return true;
    }

    public boolean selectPiece(int value)
    {
        Piece pieceValue = new Piece(0, 0, value);
        int result = Arrays.binarySearch(piecesArray, pieceValue);

        if (result >= 0)
        {
            Piece actualPiece = piecesArray[result];
            actualPiece.chosen = true;

            Integer currentColumnValueForHashMap = columnsChosen.putIfAbsent(Integer.toString(actualPiece.y), 1);

            if (currentColumnValueForHashMap != null)
            {
                columnsChosen.put(Integer.toString(actualPiece.y), currentColumnValueForHashMap + 1);
                if (currentColumnValueForHashMap + 1 == columns)
                {
                    hasBingo = true;
                }
            }

            Integer currentRowValueForHashMap = rowsChosen.putIfAbsent(Integer.toString(actualPiece.x), 1);

            if (currentRowValueForHashMap != null)
            {
                rowsChosen.put(Integer.toString(actualPiece.x), currentRowValueForHashMap + 1);
                if (currentRowValueForHashMap + 1 == rows)
                {
                    hasBingo = true;
                }
            }
            return true;
        }
        return false;
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
        } else
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
        return value == piece.value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, value, chosen);
    }
}