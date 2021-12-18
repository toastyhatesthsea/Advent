import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dive
{

    public static int diveDirection(File aFile) throws FileNotFoundException
    {
        Scanner aFileScan = new Scanner(aFile);
        String aLine;
        int xVector = 0;
        int zVector = 0;

        while(aFileScan.hasNext())
        {
            aLine = aFileScan.nextLine();
            Scanner lineScanner = new Scanner(aLine);


            while (lineScanner.hasNext())
            {
                String direction = lineScanner.next();
                int value = lineScanner.nextInt();
                String mew = "";

                if (direction.equals("forward"))
                {
                    xVector += value;
                } else if (direction.equals("up"))
                {
                    zVector -= value;
                } else if (direction.equals("down"))
                {
                    zVector += value;
                }
            }
        }
        int answer = xVector * zVector;
        return answer;
    }

    public static long diveDepth(File aFile) throws FileNotFoundException
    {
        Scanner aFileScan = new Scanner(aFile);
        String aLine;
        int xVector = 0;
        int zVector = 0;
        int depth = 0;

        while(aFileScan.hasNext())
        {
            aLine = aFileScan.nextLine();
            Scanner lineScanner = new Scanner(aLine);


            while (lineScanner.hasNext())
            {
                String direction = lineScanner.next();
                int value = lineScanner.nextInt();
                String mew = "";

                if (direction.equals("forward"))
                {
                    xVector += value;
                    depth = depth +  value * zVector;
                } else if (direction.equals("up"))
                {
                    zVector -= value;
                } else if (direction.equals("down"))
                {
                    zVector += value;
                }
            }
        }
        long answer = xVector * depth;
        return answer;
    }

    public static void main(String[] asdasdasd) throws FileNotFoundException
    {
        //Dive.diveDirection(new File("day_two"));
        Dive.diveDepth(new File("day_two"));
    }


}
