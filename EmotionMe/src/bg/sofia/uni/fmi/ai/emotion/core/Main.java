package bg.sofia.uni.fmi.ai.emotion.core;

import bg.sofia.uni.fmi.ai.emotion.core.preprocessing.DatasetReader;
import bg.sofia.uni.fmi.ai.emotion.core.preprocessing.Standardizer;

import java.util.LinkedList;
import java.util.List;

public class Main {

    // the static path to the file
    //private static final String PATH = "dataset/emotion_dataset.csv";
    // for test
    private static final String PATH = "dataset/test.csv";
    private static final int RESOLUTION_HEIGHT = 48;
    private static final int RESOLUTION_WIDTH = 48;
    private static final int FOLDS_NUMBER = 5;

    public static void main(String[] args) {
        DatasetReader ds = new DatasetReader();
        ds.readFile(PATH);
        ds.printDataset();

        // standardize features
        for (int image = 0; image < ds.getPixels().size(); image++) {
            List<List<Double>> newRow = new LinkedList<>();
            for (int row = 0; row < RESOLUTION_HEIGHT; row++) {
                List<Double> standardized = Standardizer.standardizeData(ds.getPixels().get(image).get(row));
                newRow.add(standardized);
            }

            pixels.add(newRow);
        }

        // splitting into training and testing data
        CrossValidation cv = new CrossValidation(pixels.size(), FOLDS_NUMBER);
    }
}
