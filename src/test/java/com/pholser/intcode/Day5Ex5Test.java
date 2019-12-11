package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day5Ex5Test {
    @Test void eight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("8");
        computer.loadProgram(
            new FileInputStream("src/main/resources/day5-ex5.intcode"));

        computer.run();

        assertEquals(singletonList("1"), computer.ioBuffer());
    }

    @Test void notEight() throws Exception {
        BlockingQueue<String> ioBuffer = new LinkedBlockingQueue<>();
        IntcodeComputer computer =
            new IntcodeComputer(10000, ioBuffer);

        ioBuffer.put("9");
        computer.loadProgram(
            new FileInputStream("src/main/resources/day5-ex5.intcode"));

        computer.run();

        assertEquals(singletonList("0"), computer.ioBuffer());
    }
}
