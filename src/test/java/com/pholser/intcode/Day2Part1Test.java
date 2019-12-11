package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class Day2Part1Test {
    @Test void part1() throws Exception {
        IntcodeComputer computer = new IntcodeComputer(1000);

        computer.loadProgram(
            new FileInputStream(
                "src/main/resources/1202-program-alarm.intcode"));
        computer.putValueTo(1, "12");
        computer.run();

        assertEquals("5305095", computer.valueAt(0));
    }
}
