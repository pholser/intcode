package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class Day5Part1Test {
    @Test
    void part1() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("1");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-input.intcode"));

        computer.run();

        assertEquals(
            Arrays.asList(
                "0", "0", "0", "0", "0", "0", "0", "0", "0", "15426686"),
            computer.inBuffer());
    }
}
