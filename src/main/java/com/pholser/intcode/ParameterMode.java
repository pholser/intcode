package com.pholser.intcode;

enum ParameterMode {
    POSITION("0"),
    IMMEDIATE("1"),
    RELATIVE("2");

    private final String code;

    ParameterMode(String code) {
        this.code = code;
    }

    static ParameterMode parse(String raw) {
        for (ParameterMode each : values()) {
            if (each.code.equals(raw))
                return each;
        }

        throw new IllegalArgumentException(
            "Unrecognized parameter mode: " + raw);
    }

    String code() {
        return code;
    }
}
