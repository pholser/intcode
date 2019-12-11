package com.pholser.intcode;

import static com.pholser.intcode.ParameterMode.*;

class ReadInputAndStoreInstruction extends Instruction {
    ReadInputAndStoreInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer)
        throws InterruptedException {

        computer.putValueTo(
            Integer.parseInt(resolveParameter(computer, 0)),
            computer.takeInput());
        advanceProgramCounter(computer);

        return true;
    }

    @Override String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex) {

        if (parameterIndex == 0) {
            return resolveAddressOperand(computer, parameterIndex);
        }

        throw new IllegalArgumentException(
            "Unrecognized parameter index " + parameterIndex);
    }

    private String resolveAddressOperand(
        IntcodeComputer computer,
        int parameterIndex) {

        String rawParameterValue =
            computer.valueAt(computer.pc() + parameterIndex + 1);

        if (modes().at(parameterIndex) == POSITION) {
            return rawParameterValue;
        }

        throw unsupportedParameterMode(parameterIndex);
    }
}
