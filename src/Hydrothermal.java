import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hydrothermal
{

    HydroBoard aHydroBoard;

    public Hydrothermal(int size, int overlapMin)
    {
        aHydroBoard = new HydroBoard(size, overlapMin);
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
            if (y1Int > y2Int)
            {
                int temp = y1Int;
                y1Int = y2Int;
                y2Int = temp;
            }
            aHydroBoard.parseCol(x1Int, y1Int, x2Int, y2Int);
        } else if (y1Int == y2Int)
        {
            if (x1Int > x2Int)
            {
                int temp = x1Int;
                x1Int = x2Int;
                x2Int = temp;
            }
            aHydroBoard.parseRow(x1Int, y1Int, x2Int, y2Int);
        } else
        {
            if (x1Int < x2Int)
            {
                if (y1Int < y2Int)
                {
                    aHydroBoard.parseRightDownDiagnol(x1Int, y1Int, x2Int, y2Int);
                }
                else
                {
                    aHydroBoard.parseRightUpDiagnol(x1Int, y1Int, x2Int, y2Int);
                }
            }
            else
            {
                if (y1Int < y2Int)
                {
                    aHydroBoard.parseLeftDownDiagnol(x1Int, y1Int, x2Int, y2Int);
                }
                else
                {
                    aHydroBoard.parseLeftUpDiagnol(x1Int, y1Int, x2Int, y2Int);
                }
            }
            //aHydroBoard.parseDiagnol(y1Int, y2Int, x1Int, x2Int);
        }
    }


}


class HydroBoard
{
    int[][] aBoard;
    int overlapMin;
    int numberOfOverlaps;

    public HydroBoard(int size, int aOverlapMin)
    {
        aBoard = new int[size][size];
        overlapMin = aOverlapMin;
        numberOfOverlaps = 0;
    }


    public void insertValue(int row, int column)
    {
        try
        {
            int aValue = aBoard[row][column];
            if (aValue + 1 == overlapMin)
            {
                numberOfOverlaps++;
            }
            aBoard[row][column] = aValue + 1;
        } catch (Exception e)
        {
            aBoard[row][column] = 1;
        }
    }

    public void parseRow(int x1, int y1, int x2, int y2)
    {
        while (x1 <= x2)
        {
            insertValue(y1, x1);
            x1++;
        }
    }

    public void parseDiagnol(int begRow, int endRow, int begCol, int endCol)
    {
        for (int i = begRow, j = begCol; i <= endRow || j <= endCol; i++, j++)
        {
            insertValue(i, j);
        }
    }

    public void parseRightDownDiagnol(int x1, int y1, int x2, int y2)
    {
        while (x1 <= x2)
        {
            insertValue(y1, x1);
            x1++;
            y1++;
        }
    }

    public void parseLeftDownDiagnol(int x1, int y1, int x2, int y2)
    {
        while (x1 >= x2)
        {
            insertValue(y1, x1);
            x1--;
            y1++;
        }
    }

    public void parseRightUpDiagnol(int x1, int y1, int x2, int y2)
    {
        while (y1 >= y2)
        {

            insertValue(y1, x1);
            y1--;
            x1++;
        }
    }

    public void parseLeftUpDiagnol(int x1, int y1, int x2, int y2)
    {
        while (y1 >= y2)
        {
            insertValue(y1, x1);
            y1--;
            x1--;
        }
    }

    public void parseCol(int x1, int y1, int x2, int y2)
    {
        while (y1 <= y2)
        {
            insertValue(y1, x1);
            y1++;
        }
    }
}


class HydroTesters
{
    public static void main(String[] asdasdasdsad) throws FileNotFoundException
    {
        Hydrothermal hydro = new Hydrothermal(1000, 2);
        hydro.parseData(new File("hydro_large"));

    }
}