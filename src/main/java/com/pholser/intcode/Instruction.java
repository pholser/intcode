package com.pholser.intcode;

abstract class Instruction {
    private final ParameterModes modes;
    private Opcode opcode;

    protected Instruction(ParameterModes modes, Opcode opcode) {
        this.modes = modes;
        this.opcode = opcode;
    }

    ParameterModes modes() {
        return modes;
    }

    void advanceProgramCounter(IntcodeComputer computer) {
        computer.adjustPcBy(opcode.numberOfOperands() + 1);
    }

    abstract boolean execute(IntcodeComputer computer)
        throws InterruptedException;

    abstract String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex);

    int convertToAddress(String raw) {
        return Integer.parseInt(raw);
    }

    protected AssertionError unsupportedParameterMode(int parameterIndex) {
        return new AssertionError(
            "parameter mode " + modes().at(parameterIndex)
                + " at parameter index " + parameterIndex);
    }
}
