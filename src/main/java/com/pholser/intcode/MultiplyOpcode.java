package com.pholser.intcode;

import java.math.BigInteger;

class MultiplyOpcode extends Opcode {
    MultiplyOpcode(ParameterModes modes) {
        super(modes);
    }

    @Override boolean execute(IntcodeComputer computer) {
        BigInteger val1 =
            new BigInteger(
                computer.valueAt(computer.addressAt(computer.pc() + 1)));
        BigInteger val2 =
            new BigInteger(
                computer.valueAt(computer.addressAt(computer.pc() + 2)));
        BigInteger result = val1.multiply(val2);
        computer.putValueTo(computer.addressAt(computer.pc() + 3), result);
        computer.adjustPcBy(4);

        return true;
    }
}
