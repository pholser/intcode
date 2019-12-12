package com.pholser.intcode;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class Day7Ex3Test {
    @Test void ex3() throws Exception {
        BlockingQueue<String> in1 = new LinkedBlockingQueue<>();
        in1.put("1");
        in1.put("0");
        BlockingQueue<String> out1in2 = new LinkedBlockingQueue<>();
        out1in2.put("0");
        BlockingQueue<String> out2in3 = new LinkedBlockingQueue<>();
        out2in3.put("4");
        BlockingQueue<String> out3in4 = new LinkedBlockingQueue<>();
        out3in4.put("3");
        BlockingQueue<String> out4in5 = new LinkedBlockingQueue<>();
        out4in5.put("2");
        BlockingQueue<String> out5 = new LinkedBlockingQueue<>();

        IntcodeComputer computer1 =
            new IntcodeComputer(10000, in1, out1in2);
        computer1.loadProgram(
            new FileInputStream("src/test/resources/day7-ex3.intcode"));
        IntcodeComputer computer2 =
            new IntcodeComputer(10000, out1in2, out2in3);
        computer2.loadProgram(
            new FileInputStream("src/test/resources/day7-ex3.intcode"));
        IntcodeComputer computer3 =
            new IntcodeComputer(10000, out2in3, out3in4);
        computer3.loadProgram(
            new FileInputStream("src/test/resources/day7-ex3.intcode"));
        IntcodeComputer computer4 =
            new IntcodeComputer(10000, out3in4, out4in5);
        computer4.loadProgram(
            new FileInputStream("src/test/resources/day7-ex3.intcode"));
        IntcodeComputer computer5 =
            new IntcodeComputer(10000, out4in5, out5);
        computer5.loadProgram(
            new FileInputStream("src/test/resources/day7-ex3.intcode"));

        new Thread(computer1).start();
        new Thread(computer2).start();
        new Thread(computer3).start();
        new Thread(computer4).start();
        computer5.run();

        assertEquals(singletonList("65210"), computer5.outBuffer());
    }
}
