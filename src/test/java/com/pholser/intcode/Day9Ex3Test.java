package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day9Ex3Test {
    @Test void ex3() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);
        computer.loadProgram(
            new FileInputStream("src/test/resources/day9-ex3.intcode"));

        computer.run();

        assertEquals(singletonList("1125899906842624"), computer.outBuffer());
    }
}
