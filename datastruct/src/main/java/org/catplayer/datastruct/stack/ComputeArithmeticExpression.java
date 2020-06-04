package org.catplayer.datastruct.stack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * use 2 stacks to compute an arithmetic expression
 *
 * @author catplayer
 * @since 1.0
 */
public class ComputeArithmeticExpression {

    //base operators
    private static final String ADD = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String SQRT = "sqrt";

    //parenthesis
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";


    public static double compute(String[] expressionSlices) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Double> operandsStack = new Stack<>();
        for (String expressionSlice : expressionSlices
        ) {
            switch (expressionSlice) {
                case LEFT_PARENTHESIS:
                    StdOut.println("( just ignore");
                    break;
                case ADD:
                case MINUS:
                case MULTIPLY:
                case DIVIDE:
                case SQRT: {
                    //the operators, just push to org.catplayer.datastruct.stack
                    operatorStack.push(expressionSlice);
                    break;
                }
                case RIGHT_PARENTHESIS: {
                    //the right parenthesis, we need to compute arithmetic expression which is included in the parenthesis
                    String operator = operatorStack.pop();
                    double rightValue;
                    double leftValue;
                    switch (operator) {
                        case ADD:
                            rightValue = operandsStack.pop();
                            leftValue = operandsStack.pop();
                            operandsStack.push(leftValue + rightValue);
                            break;
                        case MINUS:
                            rightValue = operandsStack.pop();
                            leftValue = operandsStack.pop();
                            operandsStack.push(leftValue - rightValue);
                            break;
                        case MULTIPLY:
                            rightValue = operandsStack.pop();
                            leftValue = operandsStack.pop();
                            operandsStack.push(leftValue * rightValue);
                            break;
                        case DIVIDE:
                            rightValue = operandsStack.pop();
                            leftValue = operandsStack.pop();
                            operandsStack.push(leftValue / rightValue);
                            break;
                        case SQRT:
                            rightValue = operandsStack.pop();
                            operandsStack.push(Math.sqrt(rightValue));
                            break;
                    }
                    break;
                }
                default:
                    //the operands, just push to org.catplayer.datastruct.stack,
                    operandsStack.push(Double.parseDouble(expressionSlice));
            }
        }

        return operandsStack.pop();
    }

    //no need to write unit test, the main method can execute test
    public static void main(String[] args) {
        System.out.println("what you want to compute is " + Arrays.toString(args).replace(",", ""));
        System.out.println("result: " + compute(args));
    }
}
