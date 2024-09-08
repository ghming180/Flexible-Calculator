package org.example;

import java.util.Map;
import java.util.function.BiFunction;

public class Calculator {
    private final Map<Operation, BiFunction<Number, Number, Number>> operationMap;

    public Calculator(Map<Operation, BiFunction<Number, Number, Number>> operationMap) {
        this.operationMap = operationMap;
    }

    public Number calculate(Operation op, Number num1, Number num2) throws UnsupportedOperationException {
        BiFunction<Number, Number, Number> operation = operationMap.get(op);
        if (operation != null) {
            return operation.apply(num1, num2);
        } else {
            throw new UnsupportedOperationException("Operation " + op + " is not supported.");
        }
    }

    // Inner class for chaining operations
    public static class ChainCalculator {
        private final Calculator calculator;
        private Number result;

        public ChainCalculator(Calculator calculator, Number initialValue) {
            this.calculator = calculator;
            this.result = initialValue;
        }

        public ChainCalculator perform(Operation op, Number num) throws UnsupportedOperationException {
            result = calculator.calculate(op, result, num);
            return this;
        }

        public Number getResult() {
            return result;
        }
    }
}
