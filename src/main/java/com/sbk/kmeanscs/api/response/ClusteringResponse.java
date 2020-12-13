package com.sbk.kmeanscs.api.response;

public class ClusteringResponse {

    public int[][] clusteredData;

    public ClusteringResponse() {

    }

    public ClusteringResponse(int[][] clusteredData) {
        this.clusteredData = clusteredData;
    }

    @Override
    public String toString() {
        return String.format(
                "Clustering Response. Data length = %s",
                clusteredData.length
        );
    }
}
