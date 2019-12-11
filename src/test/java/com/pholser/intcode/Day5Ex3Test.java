package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Ex3Test {
    @Test void inputZero() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("0");
        computer.loadProgram(
            new FileInputStream("src/main/resources/day5-ex3.intcode"));

        computer.run();

        assertEquals(singletonList("0"), computer.ioBuffer());
    }

    @Test void inputNotZero() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("1");
        computer.loadProgram(
            new FileInputStream("src/main/resources/day5-ex3.intcode"));

        computer.run();

        assertEquals(singletonList("1"), computer.ioBuffer());
    }
}
