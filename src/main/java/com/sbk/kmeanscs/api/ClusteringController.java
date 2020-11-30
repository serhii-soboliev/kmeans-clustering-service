package com.sbk.kmeanscs.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClusteringController {

    @GetMapping
    public int[][] hello() {
        return new int[][]{};
    }
}
