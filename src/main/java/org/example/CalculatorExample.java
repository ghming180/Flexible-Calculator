package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class CalculatorExample {
    public static void main(String[] args) {
        // Initialize the operation map
        Map<Operation, BiFunction<Number, Number, Number>> operationMap = new HashMap<>();
        operationMap.put(Operation.ADD, (a, b) -> a.doubleValue() + b.doubleValue());
        operationMap.put(Operation.SUBTRACT, (a, b) -> a.doubleValue() - b.doubleValue());
        operationMap.put(Operation.MULTIPLY, (a, b) -> a.doubleValue() * b.doubleValue());
        operationMap.put(Operation.DIVIDE, (a, b) -> {
            if (b.doubleValue() == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a.doubleValue() / b.doubleValue();
        });

        // Create a calculator instance
        Calculator calculator = new Calculator(operationMap);

        // Perform a basic addition calculation
        Number addResult = calculator.calculate(Operation.ADD, 10, 5);
        System.out.println("Result of 10 + 5: " + addResult);

        // Perform a basic subtraction calculation
        Number subtractResult = calculator.calculate(Operation.SUBTRACT, 10, 5);
        System.out.println("Result of 10 - 5: " + subtractResult);

        // Perform a basic multiplication calculation
        Number multiplyResult = calculator.calculate(Operation.MULTIPLY, 10, 5);
        System.out.println("Result of 10 * 5: " + multiplyResult);

        // Perform a basic division calculation
        Number divideResult = calculator.calculate(Operation.DIVIDE, 10, 5);
        System.out.println("Result of 10 / 5: " + divideResult);

        // Example of division by zero (will throw an exception)
        try {
            calculator.calculate(Operation.DIVIDE, 10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Chain multiple operations
        Calculator.ChainCalculator chainCalc = new Calculator.ChainCalculator(calculator, 20);
        Number chainResult = chainCalc.perform(Operation.SUBTRACT, 5)
                .perform(Operation.DIVIDE, 3)
                .perform(Operation.ADD, 10)
                .getResult();
        System.out.println("Result of ((20 - 5) / 3) + 10: " + chainResult);
    }
}

