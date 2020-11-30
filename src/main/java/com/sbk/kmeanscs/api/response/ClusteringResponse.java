package com.sbk.kmeanscs.api.response;

public class ClusteringResponse {
    final int[][] clusteredData;

    public ClusteringResponse(int[][] clusteredData) {
        this.clusteredData = clusteredData;
    }
}
