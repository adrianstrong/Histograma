package csur.adrian;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.apache.commons.csv.*;
public class App {
    public static void main(String[] args) {
        File input = new File("resources/LoremIpsum.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(input, StandardCharsets.UTF_8));

            String line = "";
            HashMap<String, Integer> map = new HashMap<>();
            String output = input.getParent() + input.separator
                    + input.getName().substring(0, input.getName().lastIndexOf(".")) + "_histograma.csv";

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] words = line.split(" ");
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-ZÀ-ÿ\u00f1\u00d1]", "");
                    if (word.length() <= 2) {
                        continue;
                    } else {
                        if (map.keySet().contains(word)) {
                            map.put(word, map.get(word) + 1);
                        } else {
                            map.put(word, 1);
                        }
                        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(output, StandardCharsets.UTF_8), CSVFormat.DEFAULT);
                        for (String key : map.keySet()) {
                            csvPrinter.printRecord(key, map.get(key));
                        }
                        csvPrinter.flush();
                    }
                }
                System.out.println(map);
            }
        br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

