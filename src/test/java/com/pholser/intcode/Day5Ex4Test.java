package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Ex4Test {
    @Test void lessThanEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("7");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex4.intcode"));

        computer.run();

        assertEquals(singletonList("999"), computer.ioBuffer());
    }

    @Test void eight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("8");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex4.intcode"));

        computer.run();

        assertEquals(singletonList("1000"), computer.ioBuffer());
    }

    @Test void greaterThanEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("9");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex4.intcode"));

        computer.run();

        assertEquals(singletonList("1001"), computer.ioBuffer());
    }
}
