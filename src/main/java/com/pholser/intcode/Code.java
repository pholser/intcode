package com.pholser.intcode;

enum Code {
    ADD(1, 3) {
        @Override
        Instruction makeOpcode(ParameterModes modes) {
            return new AddInstruction(modes);
        }
    },
    MULTIPLY(2, 3) {
        @Override
        Instruction makeOpcode(ParameterModes modes) {
            return new MultiplyInstruction(modes);
        }
    },
    READ_INPUT_AND_STORE(3, 1) {
        @Override
        Instruction makeOpcode(ParameterModes modes) {
            return new ReadInputAndStoreInstruction(modes);
        }
    },
    OUTPUT(4, 1) {
        @Override
        Instruction makeOpcode(ParameterModes modes) {
            return new OutputInstruction(modes);
        }
    },
    HALT(99, 0) {
        @Override
        Instruction makeOpcode(ParameterModes modes) {
            return new HaltInstruction(modes);
        }
    };

    private final int numeric;
    private final int numberOfOperands;

    Code(int numeric, int numberOfOperands) {
        this.numeric = numeric;
        this.numberOfOperands = numberOfOperands;
    }

    static Code forNumeric(int value) {
        for (Code each : values()) {
            if (each.numeric == value)
                return each;
        }

        throw new IllegalArgumentException(
            "Unrecognized numeric opcode " + value);
    }

    abstract Instruction makeOpcode(ParameterModes modes);

    int numberOfOperands() {
        return numberOfOperands;
    }
}
