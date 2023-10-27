import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main4 {


    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Передайте файл с элементами массива в виде аргумента");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            try {
                ArrayList<Integer> arrayList = new ArrayList<>();
                String line = reader.readLine();
                int average = 0;
                while (line != null) {
                    arrayList.add(Integer.parseInt(line));
                    average += Integer.parseInt(line);
                    line = reader.readLine();
                }
                average = (int) Math.round((double) average / (double) arrayList.size());
                int res = 0;
                for (int value : arrayList
                ) {
                    res += Math.abs(value - average);
                }
                System.out.println(res);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}


