import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numRest = Integer.parseInt(br.readLine());
        boolean found = false;
        String result = "Anywhere is fine I guess";

        for (int i = 0; i < numRest; i++) { //checks through restaurants
            //individual flags to check for respective food found
            boolean peasoupExist = false;
            boolean pancakesExist = false;
            int numItems = Integer.parseInt(br.readLine());
            String restaurant = br.readLine();
            for (int j = 0; j < numItems; j++) { //checks through menu items
                String item = br.readLine();
                if (item.equals("pancakes"))
                    peasoupExist = true;
                else if (item.equals("pea soup"))
                    pancakesExist = true;
                if (pancakesExist && peasoupExist) {
                    result = restaurant; //changes result to restaurant name if pea soup and pancakes found
                    found = true;
                    break; //breaks inner for loop as soon as pea soup and pancakes found
                }
            }
            if (found)
                break; //breaks outer for loop as soon as pea soup and pancakes found
        }
        System.out.println(result);
    }
}
