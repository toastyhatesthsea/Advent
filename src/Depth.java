import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Depth
{

    public int depthChecker(File aFile) throws FileNotFoundException
    {
        int answer = 0; //Answer is the counter
        int prevDepth = -1;
        Scanner aScan = new Scanner(aFile); //Enables us to go through each line

        /**
         * aScan will go through each line, one by one
         */
        while (aScan.hasNext())
        {
            int currentDepth = aScan.nextInt();

            /**
             * Ignore first line! We're not counting depth yet because there is nothing to compare
             * to in the first line!
             */
            if (prevDepth == -1)
            {
                /**
                 * Assign current depth value to previous value. So we can save the previous
                 * value for comparisons to the current value in the next loop
                 */
                prevDepth = currentDepth;
            }
            else
            {
                /**
                 * Is current value larger than the previous value?
                 * If YES, add 1 to the counter (answer)
                 */
                if (currentDepth > prevDepth)
                {
                    answer++;
                }
                /**
                 * Assign current depth value to previous value. So we can save the previous
                 * value for comparisons to the current value in the next loop
                 */
                prevDepth = currentDepth;
            }
        }

        return answer;
    }

    public static void main(String[] asldkasldkasd) throws FileNotFoundException
    {
        Depth someDepth = new Depth();
        File someFile = new File("input");
        System.out.println(someDepth.depthChecker(someFile));
    }
}
