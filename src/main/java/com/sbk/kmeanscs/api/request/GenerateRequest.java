package com.sbk.kmeanscs.api.request;

public class GenerateRequest {
    public int clusterNum;
    public int[][] bounds;

    public GenerateRequest(int clusterNum, int[][] bounds) {
        this.clusterNum = clusterNum;
        this.bounds = bounds;
    }

}
