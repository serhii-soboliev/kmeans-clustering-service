package com.sbk.kmeanscs.api;

import com.sbk.kmeanscs.algo.tasks.ClusteringTask;
import com.sbk.kmeanscs.algo.tasks.KMeansClusteringTask;
import com.sbk.kmeanscs.api.request.ClusteringRequest;
import com.sbk.kmeanscs.api.request.ClusteringType;
import com.sbk.kmeanscs.api.response.ClusteringResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusteringController {

    @GetMapping(path = "/clusterdata")
    public ClusteringResponse clusterData(@RequestBody ClusteringRequest clusteringRequest) {
        ClusteringTask clusteringTask = getClusteringTask(clusteringRequest);
        //int[][] clusteredData = clusteringTask.clusterData();
        return new ClusteringResponse(new int[][]{{100}});
    }

    private ClusteringTask getClusteringTask(ClusteringRequest clusteringRequest) {
        if(clusteringRequest.type == ClusteringType.KMEANS) {
            return new KMeansClusteringTask(clusteringRequest.data, clusteringRequest.clusterNum);
        } else {
            throw new IllegalArgumentException(
                    String.format("Clustering task for type %s not implemented yet", clusteringRequest.type)
            );
        }

    }
}
