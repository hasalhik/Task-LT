import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {


    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Укажите два аргумента");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            try {
                String line = reader.readLine();
                String[] centerCord = line.split(" ");
                float centerX = Float.parseFloat(centerCord[0]);
                float centerY = Float.parseFloat(centerCord[1]);
                line = reader.readLine();
                float radius = Float.parseFloat(line);


                try (BufferedReader reader2 = new BufferedReader(new FileReader(args[1]))) {
                    while ((line = reader2.readLine()) != null) {
                        String[] pointCord = line.split(" ");
                        float x = Float.parseFloat(pointCord[0]);
                        float y = Float.parseFloat(pointCord[1]);
                        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                        if (distance < radius) {
                            System.out.println("1");
                        } else if (distance == radius) {
                            System.out.println("0");
                        } else {
                            System.out.println("2");
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }
    }


}


