package com.pholser.intcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.stream.Collectors.*;

class IntcodeComputer {
    private final String[] memory;
    private final BlockingQueue<String> ioBuffer;

    private int pc;
    private int relativeBase;

    IntcodeComputer(int initialMemorySize) {
        this(initialMemorySize, new LinkedBlockingQueue<>());
    }

    IntcodeComputer(int initialMemorySize, BlockingQueue<String> ioBuffer) {
        this.memory = new String[initialMemorySize];
        this.ioBuffer = ioBuffer;
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

    void run() throws IOException, InterruptedException {
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
                case "3":
                    handleInput();
                    break;
                case "4":
                    handleOutput();
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

    private void handleInput() throws IOException, InterruptedException {
        putValueTo(addressAt(pc + 1), ioBuffer.take());
        pc += 2;
    }

    private void handleOutput() throws InterruptedException {
        ioBuffer.put(valueAt(addressAt(pc + 1)));
        pc += 2;
    }

    private void handleHalt() {
        // nothing to do
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

    Queue<String> ioBuffer() {
        return new LinkedList<>(ioBuffer);
    }

    private void clearMemory() {
        Arrays.fill(memory, null);
    }
}
