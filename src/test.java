import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = bufferedReader.readLine();
            String[] inputArray = input.split(", ");

            int maxValue = Integer.MIN_VALUE;
            List<Integer> inputList = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            for (String str : inputArray) {
                inputList.add(Integer.parseInt(str));
                if (Integer.parseInt(str) > maxValue) {
                    maxValue = Integer.parseInt(str);
                }
            }

            for (int i = 0; i < inputList.size(); i++) {
                if (inputList.get(i) == maxValue) {
                    indices.add(i);
                }
            }

            System.out.println(indices);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
