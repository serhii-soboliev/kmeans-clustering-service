package com.sbk.kmeanscs.api.request;

public class ClusteringRequest {
    public ClusteringType type;
    public int clusterNum;
    public int[][] data;

    @Override
    public String toString() {
        return String.format(
                "Clustering type = %s, clusters num = %s, data length = %s",
                type,
                clusterNum,
                data.length);
    }
}
