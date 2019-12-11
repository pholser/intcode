package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class Day2Part2Test {
    @Test
    void part2() throws Exception {
        int answer = Integer.MIN_VALUE;

        for (int noun = 0; noun < 100; ++noun) {
            for (int verb = 0; verb < 100; ++verb) {
                IntcodeComputer computer = new IntcodeComputer(1000);

                computer.loadProgram(
                    new FileInputStream(
                        "src/test/resources/1202-program-alarm.intcode"));
                computer.putValueTo(1, String.valueOf(noun));
                computer.putValueTo(2, String.valueOf(verb));

                computer.run();

                String result = computer.valueAt(0);
                if ("19690720".equals(result)) {
                    answer = 100 * noun + verb;
                    break;
                }
            }
        }

        assertEquals(4925, answer);
    }
}
