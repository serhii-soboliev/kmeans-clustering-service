package com.sbk.kmeanscs.algo;

import org.springframework.util.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class InboundDataGenerator {

    private static final String CSV_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.getProperty( "line.separator" );
    private static final int CLUSTER_SIZE = 100;
    private static final int POINTS_COUNT = 2;

    public int[][] generate(int clusterNum, int[][] clusterBounds) {
        Assert.isTrue(clusterNum > 0, "Cluster Number should be more than 0");
        Assert.notNull(clusterBounds, "Bounds couldn't be null");
        Assert.isTrue(clusterNum*2 == clusterBounds.length, "Each cluster should have two bounds");

        var randomizer = new Random();
        var totalSpecimenCount = CLUSTER_SIZE * clusterNum;
        var result = new int[totalSpecimenCount][POINTS_COUNT];
        for(var i=0; i<clusterNum; i++) {
            for (var j=0; j<CLUSTER_SIZE; j++) {
                var specimenIndex = i * CLUSTER_SIZE + j;
                for (int k=0; k<POINTS_COUNT; k++) {
                    var boundsIndex = i * POINTS_COUNT + k;
                    var min = clusterBounds[boundsIndex][0];
                    var max = clusterBounds[boundsIndex][1];
                    result[specimenIndex][k] = randomizer.nextInt(max - min + 1) + min;
                }
            }

        }
        return result;
    }

    public int[][] defaultGenerate() {
        var clusterNums = 3;
        var bounds = new int[][]{{100, 200},   {100, 200},
                {350, 550},   {350, 550},
                {2000, 2500}, {2000, 2500}};
        return generate(clusterNums, bounds);
    }

    void toCsv(int[][] data) {
        String csvFile = "data.csv";
        try(FileWriter writer = new FileWriter(csvFile)) {
            Arrays
                    .stream(data)
                    .map(r -> r[0] + CSV_SEPARATOR + r[1] + LINE_SEPARATOR)
                    .forEach(s -> tryToWriteToFile(writer, s));
        } catch (IOException ex) {
            System.out.println("Failed to export data to CSV");
        }
    }

    private void tryToWriteToFile(FileWriter writer, String s) {
        try {
            writer.write(s);
        } catch (IOException e) {
            System.err.printf("Failed to write %s to file", s);
        }
    }

    public static void main(String[] args) {
        var idg = new InboundDataGenerator();
        var clusterNum = 5;
        int[][] bounds ={ {100, 200},   {100, 200},
                          {350, 550},   {350, 550},
                          {800, 1000},  {800, 1000},
                          {1300, 1500}, {1300, 1500},
                          {2000, 2500}, {2000, 2500}};

        idg.toCsv(idg.generate(clusterNum, bounds));
    }
}
