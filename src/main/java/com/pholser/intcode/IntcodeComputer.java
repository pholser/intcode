package com.pholser.intcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
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

    void run() throws InterruptedException {
        boolean running = true;

        while (running) {
            Opcode opcode = new OpcodeParser().parse(valueAt(pc));
            running = opcode.execute(this);
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

    int pc() {
        return pc;
    }

    void adjustPcBy(int delta) {
        pc += delta;
    }

    Queue<String> ioBuffer() {
        return new LinkedList<>(ioBuffer);
    }

    String takeInput() throws InterruptedException {
        return ioBuffer.take();
    }

    void writeOutput(String value) throws InterruptedException {
        ioBuffer.put(value);
    }

    private void clearMemory() {
        Arrays.fill(memory, null);
    }
}
