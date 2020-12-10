package com.sbk.kmeanscs.api.request;

import java.util.Arrays;

public class GenerateRequest {
    public int clusterNum;
    public int[][] bounds;

    public GenerateRequest(int clusterNum, int[][] bounds) {
        this.clusterNum = clusterNum;
        this.bounds = bounds;
    }

    @Override
    public String toString() {
        return String.format(
                "Cluster Num = %s, bounds = %s",
                clusterNum,
                Arrays.deepToString(bounds));
    }
}
