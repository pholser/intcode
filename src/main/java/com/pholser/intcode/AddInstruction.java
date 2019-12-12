package com.pholser.intcode;

import java.math.BigInteger;

class AddInstruction extends Instruction {
    AddInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer) {
        BigInteger val1 =
            new BigInteger(resolveParameter(computer, 0));
        BigInteger val2 =
            new BigInteger(resolveParameter(computer, 1));
        BigInteger result = val1.add(val2);

        computer.putValueTo(
            Integer.parseInt(resolveParameter(computer, 2)),
            result);
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
            case RELATIVE:
                return computer.valueAt(
                    computer.relativeBase()
                        + convertToAddress(rawParameterValue));
            default:
                throw unsupportedParameterMode(parameterIndex);
        }
    }

    private String resolveAddressOperand(
        IntcodeComputer computer,
        int parameterIndex) {

        String rawParameterValue =
            computer.valueAt(computer.pc() + parameterIndex + 1);

        switch (modes().at(parameterIndex)) {
            case POSITION:
                return rawParameterValue;
            case RELATIVE:
                return String.valueOf(
                    computer.relativeBase()
                        + convertToAddress(rawParameterValue));
            default:
                throw unsupportedParameterMode(parameterIndex);
        }
    }
}
