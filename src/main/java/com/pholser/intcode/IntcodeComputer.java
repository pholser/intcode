package com.pholser.intcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static java.util.stream.Collectors.*;

class IntcodeComputer {
    private final String[] memory;
    private final BufferedReader in;
    private final PrintWriter out;

    private int pc;
    private int relativeBase;

    IntcodeComputer(int initialMemorySize, InputStream in, OutputStream out) {
        this.memory = new String[initialMemorySize];
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintWriter(new OutputStreamWriter(out), true);
    }

    void loadProgram(InputStream programIn) {
        clearMemory();

        List<String> programLines =
            new BufferedReader(new InputStreamReader(programIn)).lines()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .collect(toList());
        for (ListIterator<String> iter = programLines.listIterator();
            iter.hasNext();) {

            int cellAddress = iter.nextIndex();
            memory[cellAddress] = iter.next();
        }
    }

    void run() {
        while (true) {
            String
        }
    }

    String valueAtAddress(int address) {
        return memory[address];
    }

    private void clearMemory() {
        Arrays.fill(memory, null);
    }
}
