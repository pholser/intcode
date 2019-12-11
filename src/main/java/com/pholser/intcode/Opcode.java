package com.pholser.intcode;

enum Opcode {
    ADD(1, 3) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new AddInstruction(modes, this);
        }
    },
    MULTIPLY(2, 3) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new MultiplyInstruction(modes, this);
        }
    },
    READ_INPUT_AND_STORE(3, 1) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new ReadInputAndStoreInstruction(modes, this);
        }
    },
    OUTPUT(4, 1) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new OutputInstruction(modes, this);
        }
    },
    JUMP_IF_TRUE(5, 2) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new JumpIfTrueInstruction(modes, this);
        }
    },
    JUMP_IF_FALSE(6, 2) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new JumpIfFalseInstruction(modes, this);
        }
    },
    LESS_THAN(7, 3) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new LessThanInstruction(modes, this);
        }
    },
    EQUALS(8, 3) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new EqualsInstruction(modes, this);
        }
    },
    HALT(99, 0) {
        @Override Instruction makeOpcode(ParameterModes modes) {
            return new HaltInstruction(modes, this);
        }
    };

    private final int numeric;
    private final int numberOfOperands;

    Opcode(int numeric, int numberOfOperands) {
        this.numeric = numeric;
        this.numberOfOperands = numberOfOperands;
    }

    static Opcode forNumeric(int value) {
        for (Opcode each : values()) {
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
