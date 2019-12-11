package com.pholser.intcode;

public class HaltInstruction extends Instruction {
    public HaltInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer) {
        // nothing to do

        return false;
    }

    @Override String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex) {

        throw new UnsupportedOperationException();
    }
}
