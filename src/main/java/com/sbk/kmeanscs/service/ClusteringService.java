package com.sbk.kmeanscs.service;

import com.sbk.kmeanscs.api.request.ClusteringType;

public interface ClusteringService {

    int[][] clusterData(int[][] data, int clusterNum, ClusteringType type);
}
