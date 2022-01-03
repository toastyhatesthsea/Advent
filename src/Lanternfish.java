import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * Runs the simulation according to the amount of days
     */
    public void runSimulation()
    {
        for (int i = 0; i < amountOfDaysToSimulate; i++)
        {
            someFish.processDay();
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
    HashMap<String, Long> amountOfFish;

    public Fish()
    {
        amountOfFish = new HashMap<>();
    }

    /**
     * Returns true if value is found
     *
     * @param aKey String
     * @return boolean
     */
    public boolean addValue(String aKey)
    {
        Long currentValue = amountOfFish.putIfAbsent(aKey, 1L);

        if (currentValue != null)
        {
            amountOfFish.put(aKey, currentValue + 1);
            return true;
        }
        return false;
    }

    public void processDay()
    {
        HashMap<String, Long> tempFishes = new HashMap<>();
        int sizeOfMap = amountOfFish.size();
        for (int i = 0; i <= 8; i++)
        {
            String aKey = Integer.toString(i);

            Long aValue = amountOfFish.get(aKey);

            if (aValue != null)
            {
                if (aKey.equals("0"))
                {
                    tempFishes.put("8", aValue);

                    Long tempValue = tempFishes.putIfAbsent("6", aValue);

                    if (tempValue != null)
                    {
                        tempFishes.put("6", tempValue + aValue);
                    }
                    amountOfFish.remove("0");
                } else
                {
                    String currentKey = Integer.toString(i - 1);
                    Long value = tempFishes.putIfAbsent(currentKey, aValue);

                    if (value != null)
                    {
                        tempFishes.put(currentKey, aValue + value);
                    }

                    amountOfFish.remove(aKey);
                }
            }
        }
        amountOfFish.putAll(tempFishes);
    }

    public long amountOfCurrentFish()
    {
        long answer = 0;
        for (Map.Entry<String, Long> anEntry : amountOfFish.entrySet())
        {
            answer += anEntry.getValue();
        }
        return answer;
    }
}

class Testers
{
    public static void main(String[] asdasdasdasd) throws FileNotFoundException
    {
        Lanternfish aLatern = new Lanternfish(256);
        aLatern.parseData(new File("latern_large"));
        aLatern.runSimulation();
        long answer = aLatern.someFish.amountOfCurrentFish();
    }
}