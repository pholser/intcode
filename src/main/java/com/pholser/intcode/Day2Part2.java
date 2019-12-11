package com.pholser.intcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class Day2Part2 {
    public static void main(String[] args) throws Exception {
        for (int noun = 0; noun < 100; ++noun) {
            for (int verb = 0; verb < 100; ++verb) {
                byte[] buffer = new byte[10000];
                ByteArrayInputStream bytesIn = new ByteArrayInputStream(buffer);
                ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(10000);
                IntcodeComputer computer =
                    new IntcodeComputer(1000, bytesIn, bytesOut);

                computer.loadProgram(
                    new FileInputStream(
                        "src/main/resources/1202-program-alarm.intcode"));
                computer.putValueTo(1, String.valueOf(noun));
                computer.putValueTo(2, String.valueOf(verb));

                computer.run();

                String result = computer.valueAt(0);
                if ("19690720".equals(result)) {
                    System.out.println(100 * noun + verb);
                    break;
                }
            }
        }
    }
}
