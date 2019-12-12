package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day9Ex1Test {
    @Test void ex1() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);
        computer.loadProgram(
            new FileInputStream("src/test/resources/day9-ex1.intcode"));

        computer.run();

        assertEquals(
            asList(
                "109", "1", "204", "-1", "1001", "100", "1", "100", "1008",
                "100", "16", "101", "1006", "101", "0", "99"),
            computer.outBuffer());
    }
}
