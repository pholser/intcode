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

class IntcodeComputer implements Runnable {
    private final String[] memory;
    private final BlockingQueue<String> inBuffer;
    private final BlockingQueue<String> outBuffer;

    private int pc;
    private int relativeBase;

    IntcodeComputer(int initialMemorySize) {
        this(initialMemorySize, new LinkedBlockingQueue<>());
    }

    IntcodeComputer(
        int initialMemorySize,
        BlockingQueue<String> ioBuffer) {

        this(initialMemorySize, ioBuffer, ioBuffer);
    }

    IntcodeComputer(
        int initialMemorySize,
        BlockingQueue<String> inBuffer,
        BlockingQueue<String> outBuffer) {

        this.memory = new String[initialMemorySize];
        this.inBuffer = inBuffer;
        this.outBuffer = outBuffer;
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

    @Override public void run() {
        try {
            boolean running = true;

            while (running) {
                Instruction instruction = new OpcodeParser().parse(valueAt(pc));
                running = instruction.execute(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    String valueAt(int address) {
        return memory[address];
    }

    void putValueTo(int address, Object value) {
        memory[address] = String.valueOf(value);
    }

    int pc() {
        return pc;
    }

    void adjustPcBy(int delta) {
        pc += delta;
    }

    void jumpTo(int newPc) {
        pc = newPc;
    }

    Queue<String> inBuffer() {
        return new LinkedList<>(inBuffer);
    }

    Queue<String> outBuffer() {
        return new LinkedList<>(outBuffer);
    }

    String takeInput() throws InterruptedException {
        return inBuffer.take();
    }

    void writeOutput(String value) throws InterruptedException {
        outBuffer.put(value);
    }

    private void clearMemory() {
        Arrays.fill(memory, null);
    }
}
