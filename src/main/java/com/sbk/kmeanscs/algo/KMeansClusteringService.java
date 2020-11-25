package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.*;

public class KMeansClusteringService implements ClusteringService {

    private final int MAX_ITERATIONS = 1000;

    private final int[][] data;

    private final int clusterNum;

    public KMeansClusteringService(int[][] data, int clusterNum) {
        this.data = data;
        this.clusterNum = clusterNum;
    }

    @Override
    public int[][] clusterData() {
        var current_centroids = initializeCentroids();
        for(int k=0; k<MAX_ITERATIONS; k++) {
            for (int j=0; j<data.length; j++) {

            }
        }
        return new int[0][];
    }

    public int[][] initializeCentroids() {
        shuffleArray(data);
        return Arrays.copyOfRange(data, 0, clusterNum);
    }

    private double dist(int[] a, int[] b) {
        Assert.isTrue(a.length == b.length, "Vectors should have same length");
        double distance = 0;
        for(int i=0; i<a.length; i++) {
            distance += sqrt(pow(a[i] - b[i], 2));
        }
        return distance;
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
