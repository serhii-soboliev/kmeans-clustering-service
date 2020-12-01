package com.sbk.kmeanscs.service;

import com.sbk.kmeanscs.algo.tasks.ClusteringTask;
import com.sbk.kmeanscs.algo.tasks.KMeansClusteringTask;
import com.sbk.kmeanscs.api.request.ClusteringType;
import org.springframework.stereotype.Service;

@Service
public class ClusteringServiceImpl implements ClusteringService {

    @Override
    public int[][] clusterData(int[][] data, int clusterNum, ClusteringType type) {
        ClusteringTask clusteringTask = getClusteringTask(data, clusterNum, type);
        return clusteringTask.clusterData();
    }

    private ClusteringTask getClusteringTask(int[][] data, int clusterNum, ClusteringType type) {
        if(type == ClusteringType.KMEANS) {
            return new KMeansClusteringTask(data, clusterNum);
        } else {
            throw new IllegalArgumentException(String.format("Clustering task for type %s not implemented", type));
        }

    }

}
