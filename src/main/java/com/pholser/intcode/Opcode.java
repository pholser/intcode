package com.pholser.intcode;

abstract class Opcode {
    private final ParameterModes modes;

    protected Opcode(ParameterModes modes) {
        this.modes = modes;
    }

    ParameterModes modes() {
        return modes;
    }

    abstract boolean execute(IntcodeComputer computer)
        throws InterruptedException;
}
