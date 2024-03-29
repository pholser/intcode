package com.pholser.intcode;

class OpcodeParser {
    private final ParameterModesParser parameterModesParser =
        new ParameterModesParser();

    Instruction parse(String raw) {
        int valueNumeric = Integer.parseInt(raw);
        int opcodeNumeric = valueNumeric % 100;
        int parameterModesNumeric = valueNumeric / 100;

        Opcode opcode = Opcode.forNumeric(opcodeNumeric);
        ParameterModes modes =
            parameterModesParser.parse(opcode, parameterModesNumeric);

        return opcode.makeOpcode(modes);
    }
}
