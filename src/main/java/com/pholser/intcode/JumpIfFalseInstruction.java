package com.pholser.intcode;

import java.math.BigInteger;

import static java.math.BigInteger.*;

class JumpIfFalseInstruction extends Instruction {
    JumpIfFalseInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer) {
        BigInteger val =
            new BigInteger(resolveParameter(computer, 0));

        if (val.compareTo(ZERO) == 0) {
            int newPc = Integer.parseInt(resolveParameter(computer, 1));
            computer.jumpTo(newPc);
        } else {
            advanceProgramCounter(computer);
        }
        return true;
    }

    @Override String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex) {

        switch (parameterIndex) {
            case 0: case 1:
                return resolveValueOperand(computer, parameterIndex);
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
            case RELATIVE:
                return computer.valueAt(
                    computer.relativeBase()
                        + convertToAddress(rawParameterValue));
            default:
                throw unsupportedParameterMode(parameterIndex);
        }
    }
}
