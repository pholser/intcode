package com.pholser.intcode;

public class HaltOpcode extends Opcode {
    public HaltOpcode(ParameterModes modes) {
        super(modes);
    }

    @Override boolean execute(IntcodeComputer computer) {
        // nothing to do

        return false;
    }
}
