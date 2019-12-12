package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day9Part1And2Test {
    @Test void part1() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(30000, ioBuffer);

        ioBuffer.put("1");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day9-input.intcode"));

        computer.run();

        assertEquals(singletonList("2890527621"), computer.outBuffer());
    }

    @Test void part2() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(30000, ioBuffer);

        ioBuffer.put("2");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day9-input.intcode"));

        computer.run();

        assertEquals(singletonList("66772"), computer.outBuffer());
    }
}
