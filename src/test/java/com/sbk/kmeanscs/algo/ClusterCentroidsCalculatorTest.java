package com.sbk.kmeanscs.algo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ClusterCentroidsCalculatorTest {

    @Test
    public void expectExceptionWhenPassEmptyData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ClusterCentroidsCalculator(new int[][]{}, 1);
        });
    }

    @ParameterizedTest(name = "#{index} - Calculation Centroid For One Cluster Test")
    @MethodSource("oneClusterClusteredDataAndCentroidProvider")
    public void calculateCentroidForOneCluster(int[][] clusteredData, int[][] centroids) {
        ClusterCentroidsCalculator ccc = new ClusterCentroidsCalculator(clusteredData, 1);
        assertArrayEquals(centroids, ccc.calculateCentroids());
    }

    @ParameterizedTest(name = "#{index} - Calculation Centroid For Double Cluster Test")
    @MethodSource("doubleClusterClusteredDataAndCentroidProvider")
    public void calculateCentroidForDoubleCluster(int[][] clusteredData, int[][] centroids) {
        ClusterCentroidsCalculator ccc = new ClusterCentroidsCalculator(clusteredData, 2);
        assertArrayEquals(centroids, ccc.calculateCentroids());
    }

    static Stream<Arguments> oneClusterClusteredDataAndCentroidProvider() {
        return Stream.of(

                arguments(new int[][]{{100, 200, 1}, {50, 150, 1}},
                        new int[][]{{75, 175, 1}}),

                arguments(new int[][]{{100, 200, 1}, {50, 150, 1}, {300, 200, 1}, {500, 300, 1}},
                        new int[][]{{237, 212, 1}})
        );
    }

    static Stream<Arguments> doubleClusterClusteredDataAndCentroidProvider() {
        return Stream.of(

                arguments(new int[][]{{100, 200, 1}, {50, 150, 2}},
                        new int[][]{{100, 200, 1}, {50, 150, 2}}),

                arguments(new int[][]{{100, 200, 1}, {200, 300, 1}, {50, 150, 2}},
                        new int[][]{{150, 250, 1}, {50, 150, 2}}),

                arguments(
                        new int[][]{
                                {2, 10, 1},
                                {3, 3, 2},
                                {3, 15, 1},
                                {7, 17, 2},
                                {70, 35, 1}
                        },
                        new int[][] {
                                {25, 20, 1},
                                {5, 10, 2}
                        }
                )


        );
    }

}