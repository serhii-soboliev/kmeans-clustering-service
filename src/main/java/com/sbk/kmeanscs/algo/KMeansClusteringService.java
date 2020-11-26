package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.*;

public class KMeansClusteringService implements ClusteringService {

    private final int MAX_ITERATIONS = 1000;

    private final int[][] data;

    private final int clusterNum;

    private final int width;

    private final int height;

    public KMeansClusteringService(int[][] data, int clusterNum) {
        this.data = data;
        this.clusterNum = clusterNum;
        this.height = data.length;
        this.width = data[0].length;
    }

    @Override
    public int[][] clusterData() {
        var currentCentroids = initializeCentroids();
        var clusteredData = new int[height][width + 1];

        for (int k = 0; k < MAX_ITERATIONS; k++) {
            for (int i = 0; i < height; i++) {
                var currentPoint = data[i];
                System.arraycopy(currentPoint, 0, clusteredData[i], 0, width);
                var closestCentroid = findClosestCentroid(currentPoint, currentCentroids);
                clusteredData[i][width] = closestCentroid;
            }
        }
        return new int[0][];
    }

    private int findClosestCentroid(int[] currentPoint, int[][] centroids) {
        Assert.isTrue(centroids.length > 0, "Centroids couldn't be empty");
        var shortestDistance = dist(currentPoint, centroids[0]);
        var currentClosestCentroid = 0;
        for (int i = 1; i < centroids.length; i++) {
            var currentCentroid = centroids[i];
            var currentDistance = dist(currentPoint, currentCentroid);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                currentClosestCentroid = i;
            }
        }
        return currentClosestCentroid;
    }

    public int[][] initializeCentroids() {
        shuffleArray(data);
        return Arrays.copyOfRange(data, 0, clusterNum);
    }

    private double dist(int[] a, int[] b) {
        Assert.isTrue(a.length == b.length, "Vectors should have same length");
        double distance = 0;
        for (int i = 0; i < a.length; i++) {
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
