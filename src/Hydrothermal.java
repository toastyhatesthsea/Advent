import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hydrothermal
{
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
                Scanner firstData = new Scanner(aLine);
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
        Hydrothermal hydro = new Hydrothermal();
        hydro.parseData(new File("hydro_large"));

    }
}