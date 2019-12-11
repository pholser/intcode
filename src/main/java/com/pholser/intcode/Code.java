package com.pholser.intcode;

enum Code {
    ADD(1, 3) {
        @Override Opcode makeOpcode(ParameterModes modes) {
            return new AddOpcode(modes);
        }
    },
    MULTIPLY(2, 3) {
        @Override Opcode makeOpcode(ParameterModes modes) {
            return new MultiplyOpcode(modes);
        }
    },
    READ_INPUT_AND_STORE(3, 1) {
        @Override
        Opcode makeOpcode(ParameterModes modes) {
            return new ReadInputAndStoreOpcode(modes);
        }
    },
    OUTPUT(4, 1) {
        @Override Opcode makeOpcode(ParameterModes modes) {
            return new OutputOpcode(modes);
        }
    },
    HALT(99, 0) {
        @Override Opcode makeOpcode(ParameterModes modes) {
            return new HaltOpcode(modes);
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

    abstract Opcode makeOpcode(ParameterModes modes);

    int numberOfOperands() {
        return numberOfOperands;
    }
}
