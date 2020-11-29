package com.sbk.kmeanscs.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KMeansClusteringServiceTest {

    @Test
    void initializeCentroids() {
        var idg = new InboundDataGenerator();
        var clusterNums = 3;
        int[][] bounds = {{100, 200},   {100, 200},
                {350, 550},   {350, 550},
                {2000, 2500}, {2000, 2500}};
        var data = idg.generate(clusterNums, bounds);
        var testInstance = new KMeansClusteringService(data, clusterNums);
        var centroids = testInstance.initializeCentroids();
        assertEquals(3, centroids.length, "There should be 3 centroids");
        assertEquals(3, centroids[0].length, "Centroid must have 3 coordinates");

    }
}