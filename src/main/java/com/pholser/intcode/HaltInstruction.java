package com.pholser.intcode;

public class HaltInstruction extends Instruction {
    public HaltInstruction(ParameterModes modes) {
        super(modes);
    }

    @Override boolean execute(IntcodeComputer computer) {
        // nothing to do

        return false;
    }
}
