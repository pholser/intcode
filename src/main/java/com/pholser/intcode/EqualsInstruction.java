package com.pholser.intcode;

import java.math.BigInteger;

import static com.pholser.intcode.ParameterMode.*;

class EqualsInstruction extends Instruction {
    EqualsInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer) {
        BigInteger val1 =
            new BigInteger(resolveValueOperand(computer, 0));
        BigInteger val2 =
            new BigInteger(resolveValueOperand(computer, 1));
        computer.putValueTo(
            Integer.parseInt(resolveAddressOperand(computer, 2)),
            val1.compareTo(val2) == 0 ? 1 : 0);

        advanceProgramCounter(computer);

        return true;
    }

    @Override String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex) {

        switch (parameterIndex) {
            case 0: case 1:
                return resolveValueOperand(computer, parameterIndex);
            case 2:
                return resolveAddressOperand(computer, parameterIndex);
            default:
                throw new IllegalArgumentException(
                    "Unrecognized parameter index " + parameterIndex);
        }
    }

    private String resolveValueOperand(
        IntcodeComputer computer,
        int parameterIndex) {

        String rawParameterValue =
            computer.valueAt(computer.pc() + parameterIndex + 1);

        switch (modes().at(parameterIndex)) {
            case POSITION:
                return computer.valueAt(
                    convertToAddress(rawParameterValue));
            case IMMEDIATE:
                return rawParameterValue;
            default:
                throw unsupportedParameterMode(parameterIndex);
        }
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
