package com.pholser.intcode;

abstract class Instruction {
    private final ParameterModes modes;

    protected Instruction(ParameterModes modes) {
        this.modes = modes;
    }

    ParameterModes modes() {
        return modes;
    }

    abstract boolean execute(IntcodeComputer computer)
        throws InterruptedException;
}
