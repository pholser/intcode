package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Ex6Test {
    @Test void lessThanEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("7");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex6.intcode"));

        computer.run();

        assertEquals(singletonList("1"), computer.outBuffer());
    }

    @Test void notLessThanEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer = new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("8");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex6.intcode"));

        computer.run();

        assertEquals(singletonList("0"), computer.outBuffer());
    }
}
