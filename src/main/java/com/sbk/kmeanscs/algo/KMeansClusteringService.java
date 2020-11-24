package com.sbk.kmeanscs.algo;

import java.util.Arrays;
import java.util.Random;

public class KMeansClusteringService implements ClusteringService {

    private final int[][] data;

    private final int clusterNum;

    public KMeansClusteringService(int[][] data, int clusterNum) {
        this.data = data;
        this.clusterNum = clusterNum;
    }

    @Override
    public int[][] clusterData() {
        return new int[0][];
    }

    public int[][] initializeCentroids() {
        shuffleArray(data);
        return Arrays.copyOfRange(data, 0, clusterNum);
    }

    private void shuffleArray(int[][] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int[] a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
