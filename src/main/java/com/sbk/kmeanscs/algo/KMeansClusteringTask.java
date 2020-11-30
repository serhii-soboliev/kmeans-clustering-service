package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.ToIntFunction;

import static java.lang.Math.pow;

public class KMeansClusteringTask implements ClusteringTask {

    private final int MAX_ITERATIONS = 1000;

    private final int[][] data;

    private final int clusterCount;

    private final int width;

    private final int height;

    public KMeansClusteringTask(int[][] data, int clusterCount) {
        this.data = data;
        this.clusterCount = clusterCount;
        this.height = data.length;
        this.width = data[0].length;
    }

    @Override
    public int[][] clusterData() {
        var centroids = initializeCentroids();
        System.out.println(Arrays.deepToString(centroids));
        var clusteredData = new int[height][width + 1];

        for (int k = 0; k < MAX_ITERATIONS; k++) {
            for (int i = 0; i < height; i++) {
                var currentPoint = data[i];
                System.arraycopy(currentPoint, 0, clusteredData[i], 0, width);
                var closestCentroid = findClosestCentroid(currentPoint, centroids);
                clusteredData[i][width] = closestCentroid;
            }
            var newCentroids = calculateNewCentroids(clusteredData);
            if(Arrays.deepEquals(centroids, newCentroids)) {
                break;
            }
            centroids = newCentroids;
        }
        sortByCoordinatesAndNumerateClusters(clusteredData);
        return clusteredData;
    }

    private void sortByCoordinatesAndNumerateClusters(int[][] clusteredData) {
        ToIntFunction<int[]> firstCoordinateComparing = o -> o[0];
        ToIntFunction<int[]> secondCoordinateComparing = o -> o[1];
        ToIntFunction<int[]> clusterComparingFunc = o -> o[width];
        Comparator<int[]> c = Comparator.comparingInt(firstCoordinateComparing)
                .thenComparingInt(secondCoordinateComparing)
                .thenComparingInt(clusterComparingFunc);
        Arrays.sort(clusteredData, c);
        var oldCluster = clusteredData[0][width];
        var newCluster = 0;
        clusteredData[0][width] = newCluster;
        for(int i=1; i<height; i++) {
            if(clusteredData[i][width] != oldCluster) {
                oldCluster = clusteredData[i][width];
                 newCluster += 1;
            }
            clusteredData[i][width] = newCluster;
        }
    }

    private int[][] calculateNewCentroids(int[][] clusteredData) {
        return new ClusterCentroidsCalculator(clusteredData).calculateCentroids();
    }

    private int findClosestCentroid(int[] currentPoint, int[][] centroids) {
        Assert.isTrue(centroids.length > 0, "Centroids couldn't be empty");
        var shortestDistance = dist(currentPoint, centroids[0]);
        var currentClosestCentroid = centroids[0][width];
        for (int i = 1; i < centroids.length; i++) {
            var currentCentroid = centroids[i];
            var currentDistance = dist(currentPoint, currentCentroid);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                currentClosestCentroid = centroids[i][width];
            }
        }
        return currentClosestCentroid;
    }

    public int[][] initializeCentroids() {
        int[][] res = new int[clusterCount][width + 1];
        shuffleArray(data);
        for(int i=0; i<clusterCount; i++) {
            System.arraycopy(data[i], 0, res[i], 0, width);
            res[i][width] = i;
        }
        return res;
    }

    private double dist(int[] point, int[] centroid) {
        Assert.isTrue(point.length + 1 == centroid.length, "Vectors should have same length");
        double distance = 0;
        for (int i = 0; i < point.length; i++) {
            distance += pow(point[i] - centroid[i], 2);
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
