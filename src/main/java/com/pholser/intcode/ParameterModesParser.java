package com.pholser.intcode;

import com.google.common.base.Strings;

import java.util.Arrays;

import static com.pholser.intcode.ParameterMode.*;
import static java.util.stream.Collectors.*;

class ParameterModesParser {
    ParameterModes parse(Opcode opcode, int parameterModesNumeric) {
        StringBuilder buffer = new StringBuilder();
        return new ParameterModes(
            Arrays.stream(
                buffer.append(parameterModesNumeric)
                    .reverse()
                    .append(
                        opcode.numberOfOperands() == 0
                            ? ""
                            : Strings.repeat(
                                POSITION.code(),
                                opcode.numberOfOperands() - buffer.length()))
                    .toString()
                    .split(""))
                .map(ParameterMode::parse)
                .collect(toList()));
    }
}
