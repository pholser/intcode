package com.pholser.intcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class Day2Part1 {
    public static void main(String[] args) throws Exception {
        byte[] buffer = new byte[10000];
        ByteArrayInputStream bytesIn = new ByteArrayInputStream(buffer);
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(10000);
        IntcodeComputer computer =
            new IntcodeComputer(1000, bytesIn, bytesOut);

        computer.loadProgram(
            new FileInputStream(
                "src/main/resources/1202-program-alarm.intcode"));
        computer.putValueTo(1, "12");
        computer.run();

        String x = computer.valueAt(0);
        System.out.println(x);
    }
}
