package com.pholser.intcode;

class OutputInstruction extends Instruction {
    OutputInstruction(ParameterModes modes) {
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
