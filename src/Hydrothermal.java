import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hydrothermal
{

    HydroBoard aHydroBoard;

    public Hydrothermal(int size)
    {
        aHydroBoard = new HydroBoard(size);
    }



    public void parseData(File aFile) throws FileNotFoundException
    {
        Scanner aFileScanner = new Scanner(aFile);

        while (aFileScanner.hasNext())
        {
            Scanner aLineScan = new Scanner(aFileScanner.nextLine());
            aLineScan.useDelimiter("->");

            while (aLineScan.hasNext())
            {
                String aLine = aLineScan.next();
                Scanner dataScanner = new Scanner(aLine);
                dataScanner.useDelimiter(",");

                String x1 = dataScanner.next();
                String y1 = dataScanner.next();
                y1 = y1.strip();

                aLine = aLineScan.next();
                dataScanner = new Scanner(aLine);
                dataScanner.useDelimiter(",");

                String x2 = dataScanner.next();
                x2 = x2.strip();
                String y2 = dataScanner.next();

                dataMover(x1, y1, x2, y2);

            }
        }

    }

    public void dataMover(String x1, String y1, String x2, String y2)
    {
        int x1Int = Integer.parseInt(x1);
        int y1Int = Integer.parseInt(y1);
        int x2Int = Integer.parseInt(x2);
        int y2Int = Integer.parseInt(y2);

        if (x1Int == x2Int)
        {
            if (y1Int < y2Int)
            {
                aHydroBoard.parseRow(x1Int, y1Int, y2Int);
            }
        }
    }

}


class HydroBoard
{
    int[][] aBoard;

    public HydroBoard(int size)
    {
        aBoard = new int[size][size];
    }


    public void insertValue(int row, int column)
    {
        try
        {
            int aValue = aBoard[row][column];
            aBoard[row][column] = aValue + 1;
        } catch (Exception e)
        {
            aBoard[row][column] = 1;
        }
    }

    public void parseRow(int row, int begCol, int endCol)
    {
        for (int i = begCol; i <= endCol; i++)
        {
            insertValue(row, i);
        }
    }

    public void parseCol(int col, int begRow, int endRow)
    {
        for (int i = begRow; i <= endRow; i++)
        {
            insertValue(i, col);
        }
    }
}


class HydroTesters
{
    public static void main(String[] asdasdasdsad) throws FileNotFoundException
    {
        Hydrothermal hydro = new Hydrothermal(1000);
        hydro.parseData(new File("hydro_large"));

    }
}