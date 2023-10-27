import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str).append(" ");
        }
        String regex = "\\d+";
        Matcher matcher = Pattern.compile(regex).matcher(sb.toString());
        int n = 0;
        while (matcher.find()) {
            String match = matcher.group();
            if (n == 0) {
                n = Integer.parseInt(match);
            } else {
                System.out.println(Task1(n, Integer.parseInt(match)));
                break;
            }
        }
    }

    private static String Task1(int n, int m) {
        if (n < 1 || m < 1) return "";
        ArrayList<Integer> list = new ArrayList<>();
        int valueOfElement = 0;
        StringBuilder res = new StringBuilder();
        do {
            for (int i = 0; i < m; i++) {
                if (valueOfElement >= n)
                    valueOfElement = 1;
                else valueOfElement++;
                list.add(valueOfElement);
            }
            valueOfElement--;
            res.append(list.get(list.size() - m));
        } while (list.get(list.size() - 1) != 1);
        return res.toString();
    }
}


