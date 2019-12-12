package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Part2Test {
    @Test
    void part2() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("5");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-input.intcode"));

        computer.run();

        assertEquals(singletonList("11430197"), computer.inBuffer());
    }
}
