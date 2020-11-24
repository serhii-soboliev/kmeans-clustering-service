package com.sbk.kmeanscs.algo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InboundDataGeneratorTest {


    @Test
    @DisplayName("Should return array 300x2")
    void shouldReturn2dArray300x2() {
        InboundDataGenerator testInstance = new InboundDataGenerator();
        var clusterNums = 3;
        int[][] bounds = {{100, 200},   {100, 200},
                          {350, 550},   {350, 550},
                          {2000, 2500}, {2000, 2500}};

        var data = testInstance.generate(clusterNums, bounds);
        assertEquals(300, data.length, "Data height should be equal to 300");
        assertEquals(2,    data[1].length, "Data width should be equal to 300");
    }
}