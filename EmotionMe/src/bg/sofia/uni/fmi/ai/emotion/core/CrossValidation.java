package bg.sofia.uni.fmi.ai.emotion.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CrossValidation {
    // indexes divided into k groups
    private final int[][] foldsIndexes;

    public CrossValidation(int datasetSize, int k) {
        Integer[] indexes = new Integer[datasetSize];
        Arrays.setAll(indexes, i -> i);
        List<Integer> asList = Arrays.asList(indexes);
        Collections.shuffle(asList);
        asList.toArray(indexes);

        this.foldsIndexes = new int[k][];
        int foldSize = datasetSize / k;
        int currentIndex = 0;

        for (int i = 0; i < k - 1; i++) {
            this.foldsIndexes[i] = new int[foldSize];

            for (int j = 0; j < foldSize; j++) {
                this.foldsIndexes[i][j] = indexes[currentIndex++];
            }
        }

        this.foldsIndexes[k - 1] = new int[foldSize + datasetSize % k];
        int helper = 0;

        for (int i = currentIndex; i < datasetSize; i++) {
            this.foldsIndexes[k - 1][helper++] = indexes[currentIndex++];
        }
    }

    public void printIndexes() {
        for (int i = 0; i < foldsIndexes.length; i++) {
            for (int j = 0; j < foldsIndexes[i].length; j++) {
                System.out.print(foldsIndexes[i][j] + " ");
            }
            System.out.println();
        }
    }
}
