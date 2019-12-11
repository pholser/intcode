package com.pholser.intcode;

import java.util.List;

import static com.pholser.intcode.ParameterMode.*;

class ParameterModes {
    private final List<ParameterMode> modes;

    public ParameterModes(List<ParameterMode> modes) {
        this.modes = modes;
    }

    ParameterMode at(int index) {
        return index >= modes.size() ? POSITION : modes.get(index);
    }
}
