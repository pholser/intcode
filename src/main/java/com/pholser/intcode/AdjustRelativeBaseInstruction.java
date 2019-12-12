package com.pholser.intcode;

class AdjustRelativeBaseInstruction extends Instruction {
    AdjustRelativeBaseInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer) {
        int delta = Integer.parseInt(resolveParameter(computer, 0));

        computer.adjustRelativeBaseBy(delta);
        advanceProgramCounter(computer);

        return true;
    }

    @Override String resolveParameter(
        IntcodeComputer computer,
        int parameterIndex) {

        if (parameterIndex == 0) {
            return resolveValueOperand(computer, parameterIndex);
        }

        throw new IllegalArgumentException(
            "Unrecognized parameter index " + parameterIndex);
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
