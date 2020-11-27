package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Comparator;

public class ClusterCentroidsCalculator {

    private final int[][] clusteredData;

    private final int clusterColumnNum;

    public ClusterCentroidsCalculator(int[][] clusteredData) {
        Assert.isTrue(clusteredData.length > 0, "Clustered data could not be empty");
        this.clusteredData = clusteredData;
        this.clusterColumnNum = clusteredData[0].length - 1;
    }

    public int[][] calculateCentroids() {
        Arrays.sort(clusteredData, Comparator.comparingDouble(o -> o[clusterColumnNum]));
        return null;
    }
}

