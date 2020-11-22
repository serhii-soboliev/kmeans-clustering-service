package com.sbk.kmeanscs.algo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class InboundDataGenerator {

    private static final String CSV_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.getProperty( "line.separator" );
    private static final int CLUSTER_SIZE = 100;
    private static final int POINTS_COUNT = 2;

    int[][] generate(int clusterNum, int[][] clusterBounds) {
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

    void print(int[][] data) {
        for (int[] s : data) {
            System.out.printf("[%s, %s]%n", s[0], s[1]);
        }
    }

    void toCsv(int[][] data) {
        String csvFile = "data.csv";
        try(FileWriter writer = new FileWriter(csvFile)) {
            for(int[] specimen : data) {
                var row =  Arrays
                        .stream(specimen)
                        .mapToObj(String::valueOf)
                        .reduce((a, b) -> a.concat(CSV_SEPARATOR).concat(b).concat(LINE_SEPARATOR))
                        .get();
                writer.write(row);
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failed to export data to CSV");
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
