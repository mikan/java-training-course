/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch22.ex22_15;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishCalc {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("+");
        list.add("sqrt");
        for (String s : list)
            System.out.print(s + " ");
        System.out.println("= " + new ReversePolishCalc().calc(list));
    }

    /**
     * Calculate.
     * 
     * @param list list of elements
     * @return result
     */
    public double calc(List<String> list) {
        Stack<Double> stack = new Stack<>();
        double e1 = 0;
        double e2 = 0;
        for (String s : list) {
            switch (s) {
            case "+":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(e2 + e1);
                break;
            case "-":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(e2 - e1);
                break;
            case "/":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(e2 / e1);
                break;
            case "*":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(e2 * e1);
                break;
            case "%":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(e2 % e1);
                break;
            case "sin":
                stack.push(Math.sin(stack.pop()));
                break;
            case "cos":
                stack.push(Math.cos(stack.pop()));
                break;
            case "tan":
                stack.push(Math.tan(stack.pop()));
                break;
            case "asin":
                stack.push(Math.asin(stack.pop()));
                break;
            case "acos":
                stack.push(Math.acos(stack.pop()));
                break;
            case "atan":
                stack.push(Math.atan(stack.pop()));
                break;
            case "atan2":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(Math.atan2(e2, e1));
                break;
            case "toRadians":
                stack.push(Math.toRadians(stack.pop()));
                break;
            case "toDegrees":
                stack.push(Math.toDegrees(stack.pop()));
                break;
            case "exp":
                stack.push(Math.exp(stack.pop()));
                break;
            case "sinh":
                stack.push(Math.sinh(stack.pop()));
                break;
            case "cosh":
                stack.push(Math.cosh(stack.pop()));
                break;
            case "tanh":
                stack.push(Math.tanh(stack.pop()));
                break;
            case "pow":
                stack.push(Math.pow(stack.pop(), stack.pop()));
                break;
            case "log":
                stack.push(Math.log(stack.pop()));
                break;
            case "log10":
                stack.push(Math.log10(stack.pop()));
                break;
            case "sqrt":
                stack.push((Math.sqrt(stack.pop())));
                break;
            case "cbrt":
                stack.push(Math.cbrt(stack.pop()));
                break;
            case "signum":
                stack.push(Math.signum(stack.pop()));
                break;
            case "ceil":
                stack.push(Math.ceil(stack.pop()));
                break;
            case "floor":
                stack.push(Math.floor(stack.pop()));
                break;
            case "rint":
                stack.push(Math.rint(stack.pop()));
                break;
            case "round":
                stack.push((double) Math.round(stack.pop()));
                break;
            case "abs":
                stack.push(Math.abs(stack.pop()));
                break;
            case "max":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(Math.max(e2, e1));
                break;
            case "min":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(Math.min(e2, e1));
                break;
            case "hypot":
                e1 = stack.pop();
                e2 = stack.pop();
                stack.push(Math.hypot(e2, e1));
                break;
            default:
                stack.push(Double.parseDouble(s));
            }
        }
        return stack.pop();
    }
}
