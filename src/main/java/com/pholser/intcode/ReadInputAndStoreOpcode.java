package com.pholser.intcode;

class ReadInputAndStoreOpcode extends Opcode {
    ReadInputAndStoreOpcode(ParameterModes modes) {
        super(modes);
    }

    @Override boolean execute(IntcodeComputer computer)
        throws InterruptedException {

        computer.putValueTo(
            computer.addressAt(computer.pc() + 1), computer.takeInput());
        computer.adjustPcBy(2);

        return true;
    }
}
