import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Lanternfish
{

    Fish someFish;
    int amountOfDaysToSimulate;

    public void parseData(File aFile) throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(aFile);

        fileScanner.useDelimiter(",");

        while (fileScanner.hasNext())
        {
            String aFishKey = fileScanner.next();
            aFishKey = aFishKey.strip();
            someFish.addValue(aFishKey);
        }

    }

    public Lanternfish(int amountOfDays)
    {
        amountOfDaysToSimulate = amountOfDays;
        someFish = new Fish();
    }

}


class Fish
{
    HashMap<String, Integer> amountOfFish;

    public Fish()
    {
        amountOfFish = new HashMap<>();
    }

    /**
     * Returns true if value is found
     * @param aKey String
     * @return boolean
     */
    public boolean addValue(String aKey)
    {
        Integer currentValue = amountOfFish.putIfAbsent(aKey, 1);

        if (currentValue != null)
        {
            amountOfFish.put(aKey, currentValue + 1);
            return true;
        }
        return false;
    }
}

class Testers
{
    public static void main(String[] asdasdasdasd) throws FileNotFoundException
    {
        Lanternfish aLatern = new Lanternfish(80);
        aLatern.parseData(new File("latern_large"));
    }
}