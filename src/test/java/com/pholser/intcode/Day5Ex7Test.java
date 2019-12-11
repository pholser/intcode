package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Ex7Test {
    @Test void eight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("8");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex7.intcode"));

        computer.run();

        assertEquals(singletonList("1"), computer.ioBuffer());
    }

    @Test void notEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("10");
        computer.loadProgram(
            new FileInputStream("src/test/resources/day5-ex7.intcode"));

        computer.run();

        assertEquals(singletonList("0"), computer.ioBuffer());
    }
}
