package com.pholser.intcode;

class OutputInstruction extends Instruction {
    OutputInstruction(ParameterModes modes, Opcode opcode) {
        super(modes, opcode);
    }

    @Override boolean execute(IntcodeComputer computer)
        throws InterruptedException {

        computer.writeOutput(
            resolveParameter(computer, 0));
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
            default:
                throw unsupportedParameterMode(parameterIndex);
        }
    }
}
