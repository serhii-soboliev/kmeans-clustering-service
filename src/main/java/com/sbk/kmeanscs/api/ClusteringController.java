package com.sbk.kmeanscs.api;

import com.sbk.kmeanscs.algo.InboundDataGenerator;
import com.sbk.kmeanscs.api.request.ClusteringRequest;
import com.sbk.kmeanscs.api.request.GenerateRequest;
import com.sbk.kmeanscs.api.response.ClusteringResponse;
import com.sbk.kmeanscs.api.response.GenerateResponse;
import com.sbk.kmeanscs.service.ClusteringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusteringController {

    private final static Logger LOG = LoggerFactory.getLogger(ClusteringController.class);

    private final ClusteringService clusteringService;

    public ClusteringController(ClusteringService clusteringService) {
        this.clusteringService = clusteringService;
    }

    @PostMapping(path = "/clusterdata")
    public ClusteringResponse clusterData(@RequestBody ClusteringRequest clusteringRequest) {
        LOG.info("Clustering request body: {}", clusteringRequest);
        var res = clusteringService.clusterData(clusteringRequest.data, clusteringRequest.clusterNum, clusteringRequest.type);
        var clusteringResponse = new ClusteringResponse(res);
        LOG.info("Clustering response body: {}", clusteringRequest);
        return clusteringResponse;
    }

    @PostMapping(path = "/generatedata")
    public GenerateResponse generateData(@RequestBody GenerateRequest generateRequest) {
        LOG.info("Generate request body: {}", generateRequest);
        var inboundDataGenerator = new InboundDataGenerator();
        var generatedData = inboundDataGenerator.generate(generateRequest.clusterNum, generateRequest.bounds);
        return new GenerateResponse(generatedData);
    }

    @PostMapping(path = "/generatedata/default")
    public GenerateResponse generateDataDefault() {
        var inboundDataGenerator = new InboundDataGenerator();
        var generatedData = inboundDataGenerator.defaultGenerate();
        return new GenerateResponse(generatedData);
    }

}
