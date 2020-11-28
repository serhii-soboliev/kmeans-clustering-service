package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ClusterCentroidsCalculator {

    private final int[][] clusteredData;

    private final int clusterColumnNum;

    private final int dimensionsNum;

    private final int clusterCount;

    public ClusterCentroidsCalculator(int[][] clusteredData, int clusterCount) {
        Assert.isTrue(clusteredData.length > 0, "Clustered data could not be empty");
        this.clusteredData = clusteredData;
        this.clusterColumnNum = clusteredData[0].length - 1;
        this.dimensionsNum = clusteredData[0].length - 1;
        this.clusterCount = clusterCount;
    }

    public int[][] calculateCentroids() {
        var res = new int[clusterCount][dimensionsNum+1];
        Arrays.sort(clusteredData, Comparator.comparingDouble(o -> o[clusterColumnNum]));
        var clusterIdx = clusteredData[0][clusterColumnNum];
        var clusterNum = 0;
        var clusterSize = 1;
        var clusterAcc = zeroArr();
        System.arraycopy(clusteredData[0], 0, clusterAcc, 0, dimensionsNum);
        for(int i=1; i<clusteredData.length; i++) {
            var currentClusterIdx = clusteredData[i][clusterColumnNum];
            if (currentClusterIdx != clusterIdx) {
                var curCentroid = calculateCentroidForCluster(clusterIdx, clusterSize, clusterAcc);
                res[clusterNum] = curCentroid;
                clusterIdx = currentClusterIdx;
                clusterNum += 1;
                clusterSize = 1;
                clusterAcc = zeroArr();
                var currentPoint = clusteredData[i];
                addArrays(currentPoint, clusterAcc);
            } else {
                clusterSize += 1;
                var currentPoint = clusteredData[i];
                addArrays(currentPoint, clusterAcc);
            }
        }
        var curCentroid = calculateCentroidForCluster(clusterIdx, clusterSize, clusterAcc);
        res[clusterNum] = curCentroid;
        return res;

    }

    private int[] zeroArr() {
        return new int[dimensionsNum];
    }

    private void addArrays(int[] currentPoint, int[] clusterAcc) {
        for(int i=0; i<clusterAcc.length; i++) {
            clusterAcc[i] += currentPoint[i];
        }
    }

    private int[] calculateCentroidForCluster(int clusterIdx, int clusterSize, int[] clusterAcc) {
        var res = new int[clusterAcc.length + 1];
        for(int i=0;i<clusterAcc.length;i++) {
            res[i] = clusterAcc[i]/clusterSize;
        }
        res[clusterAcc.length] = clusterIdx;
        return res;
    }
}

