package com.pholser.intcode;

class OutputOpcode extends Opcode {
    OutputOpcode(ParameterModes modes) {
        super(modes);
    }

    @Override boolean execute(IntcodeComputer computer)
        throws InterruptedException {

        computer.writeOutput(
            computer.valueAt(computer.addressAt(computer.pc() + 1)));
        computer.adjustPcBy(2);

        return true;
    }
}
