package com.pholser.intcode;

import com.google.common.collect.Collections2;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class Day7Part1And2Test {
    @Test void part1() {
        assertEquals(
            273814,
            runWithAllPhaseSettings(0, 4, this::runPart1WithPhaseSettings));
    }

    private int runWithAllPhaseSettings(
        int lo,
        int hi,
        Function<List<String>, Integer> fn) {

        return phaseSettingPermutations(lo, hi).stream()
            .map(fn)
            .max(Comparator.naturalOrder())
            .orElseThrow(() -> new AssertionError("no max"));
    }

    private int runPart1WithPhaseSettings(List<String> settings) {
        try {
            BlockingQueue<String> in1 = new LinkedBlockingQueue<>();
            in1.put(settings.get(0));
            BlockingQueue<String> out1in2 = new LinkedBlockingQueue<>();
            out1in2.put(settings.get(1));
            BlockingQueue<String> out2in3 = new LinkedBlockingQueue<>();
            out2in3.put(settings.get(2));
            BlockingQueue<String> out3in4 = new LinkedBlockingQueue<>();
            out3in4.put(settings.get(3));
            BlockingQueue<String> out4in5 = new LinkedBlockingQueue<>();
            out4in5.put(settings.get(4));
            BlockingQueue<String> out5 = new LinkedBlockingQueue<>();

            IntcodeComputer computer1 =
                new IntcodeComputer(10000, in1, out1in2);
            computer1.loadProgram(
                new FileInputStream("src/test/resources/day7-input.intcode"));
            IntcodeComputer computer2 =
                new IntcodeComputer(10000, out1in2, out2in3);
            computer2.loadProgram(
                new FileInputStream("src/test/resources/day7-input.intcode"));
            IntcodeComputer computer3 =
                new IntcodeComputer(10000, out2in3, out3in4);
            computer3.loadProgram(
                new FileInputStream("src/test/resources/day7-input.intcode"));
            IntcodeComputer computer4 =
                new IntcodeComputer(10000, out3in4, out4in5);
            computer4.loadProgram(
                new FileInputStream("src/test/resources/day7-input.intcode"));
            IntcodeComputer computer5 =
                new IntcodeComputer(10000, out4in5, out5);
            computer5.loadProgram(
                new FileInputStream("src/test/resources/day7-input.intcode"));

            in1.put("0");

            new Thread(computer1).start();
            new Thread(computer2).start();
            new Thread(computer3).start();
            new Thread(computer4).start();
            computer5.run();

            return Integer.parseInt(computer5.outBuffer().element());
        } catch (InterruptedException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Collection<List<String>> phaseSettingPermutations(int lo, int hi) {
        List<String> settings =
            IntStream.rangeClosed(lo, hi)
                .mapToObj(Integer::toString)
                .collect(toList());
        return Collections2.orderedPermutations(settings);
    }
}
