package com.sbk.kmeanscs.algo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class KMeansClusteringTaskTest {

    @ParameterizedTest(name = "#{index} - Clustering Data Test")
    @Disabled
    @MethodSource("dataAndClusteredDataProvider")
    public void clusteringData(int[][] data, int clusterNum, int[][] clusteredData) {
        ClusteringTask testingInstance = new KMeansClusteringTask(data, clusterNum);
        int[][] expected = testingInstance.clusterData();
        System.out.println(Arrays.deepToString(expected));
        assertArrayEquals(expected, clusteredData);
    }

    static Stream<Arguments> dataAndClusteredDataProvider() {
        return Stream.of(

                arguments(
                        new int[][]{
                                {2, 10},
                                {40, 130},
                                {3, 15},
                                {70, 170},
                                {50, 160},
                                {1, 9}
                        },
                        2,
                        new int[][]{
                                {1, 9, 0},
                                {2, 10, 0},
                                {3, 15, 0},
                                {40, 130, 1},
                                {50, 160, 1},
                                {70, 170, 1}
                        }

                ),

                arguments(
                        new int[][]{
                                {2, 10},
                                {40, 130},
                                {1190, 1200},
                                {3, 15},
                                {70, 170},
                                {1190, 1210},
                                {50, 160},
                                {1, 9},
                                {1200, 1220}
                        },
                        3,
                        new int[][]{
                                {1, 9, 0},
                                {2, 10, 0},
                                {3, 15, 0},
                                {40, 130, 1},
                                {50, 160, 1},
                                {70, 170, 1},
                                {1190, 1200, 2},
                                {1190, 1210, 2},
                                {1200, 1220, 2}
                        }

                )



        );
    }
}