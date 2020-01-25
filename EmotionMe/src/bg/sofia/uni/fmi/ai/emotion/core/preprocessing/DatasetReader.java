package bg.sofia.uni.fmi.ai.emotion.core.preprocessing;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatasetReader {

    // 48x48-pixel resolution
    private static final int RESOLUTION_HEIGHT = 48;
    private static final int RESOLUTION_WIDTH = 48;

    // the first column of the file
    private List<Integer> emotions = new LinkedList<>();
    // the second column of the file
    private List<List<List<Integer>>> pixels = new LinkedList<>();
    // the third column of the file
    private List<String> usage = new LinkedList<>();

    public List<Integer> getEmotions() {
        return emotions;
    }

    public void setEmotions(List<Integer> emotions) {
        this.emotions = emotions;
    }

    public List<List<List<Integer>>> getPixels() {
        return pixels;
    }

    public void setPixels(List<List<List<Integer>>> pixels) {
        this.pixels = pixels;
    }

    public List<String> getUsage() {
        return usage;
    }

    public void setUsage(List<String> usage) {
        this.usage = usage;
    }

    public DatasetReader() {}

    @Contract(pure = true)
    // executes the reading from a file and stores the data in a new dataset
    public void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            // get a whole line from the dataset file
            String line = bufferedReader.readLine();
            // starting from the second line
            line = bufferedReader.readLine();
            while (line != null) {
                // if the line is empty, continue with the next one
                if (line.isEmpty()) {
                    line = bufferedReader.readLine();
                    continue;
                }

                // get the data line by line
                if (!line.split(",")[0].isBlank()) {
                    this.emotions.add(Integer.parseInt(line.split(",")[0]));
                }
                if (!line.split(",")[1].isBlank()) {
                    this.pixels.add(listToResolutionMatrix(Stream
                            .of(line.split(",")[1].split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())));
                }
                if (!line.split(",")[2].isBlank()) {
                    this.usage.add(line.split(",")[2]);
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Contract(pure = true)
    private List<List<Integer>> listToResolutionMatrix(List<Integer> pixels) {
        List<List<Integer>> result = new LinkedList<>();
        int index = 0;
        for (int row = 0; row < RESOLUTION_WIDTH; row++) {
            List<Integer> cols = new LinkedList<>();
            for (int col = 0; col < RESOLUTION_HEIGHT; col++) {
                cols.add(pixels.get(index));
                index++;
            }
            result.add(cols);
        }
        return result;
    }

    public void printDataset() {
        System.out.println(this.emotions.get(0));
        for (int l = 0; l < this.pixels.size(); l++) {
            for (int i = 0; i < RESOLUTION_WIDTH; i++) {
                for (int j = 0; j < RESOLUTION_HEIGHT; j++) {
                    System.out.print(this.pixels.get(l).get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
        System.out.println(this.usage.get(0));
    }
}
