package com.pholser.intcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        boolean running = true;

        while (running) {
            String opcode = valueAt(pc);
            switch (opcode) {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleMultiply();
                    break;
                case "99":
                    handleHalt();
                    running = false;
                    break;
                default:
                    throw new IllegalStateException(
                        String.format(
                            "Unrecognized opcode at pc %d: %s",
                            pc,
                            opcode));
            }
        }
    }

    private void handleAdd() {
        BigInteger val1 =
            new BigInteger(valueAt(addressAt(pc + 1)));
        BigInteger val2 =
            new BigInteger(valueAt(addressAt(pc + 2)));
        BigInteger result = val1.add(val2);
        putValueTo(addressAt(pc + 3), result);
        pc += 4;
    }

    private void handleMultiply() {
        BigInteger val1 =
            new BigInteger(valueAt(addressAt(pc + 1)));
        BigInteger val2 =
            new BigInteger(valueAt(addressAt(pc + 2)));
        BigInteger result = val1.multiply(val2);
        putValueTo(addressAt(pc + 3), result);
        pc += 4;
    }

    private void handleHalt() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String valueAt(int address) {
        return memory[address];
    }

    void putValueTo(int address, Object value) {
        memory[address] = String.valueOf(value);
    }

    int addressAt(int address) {
        return Integer.parseInt(valueAt(address));
    }

    private void clearMemory() {
        Arrays.fill(memory, null);
    }
}
