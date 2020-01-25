package bg.sofia.uni.fmi.ai.emotion.core;

import bg.sofia.uni.fmi.ai.emotion.core.preprocessing.DatasetReader;

public class Main {

    // the static path to the file
    //private static final String PATH = "dataset/emotion_dataset.csv";
    // for test
    private static final String PATH = "dataset/test.csv";

    public static void main(String[] args) {
        DatasetReader ds = new DatasetReader();
        ds.readFile(PATH);
        ds.printDataset();
    }
}
