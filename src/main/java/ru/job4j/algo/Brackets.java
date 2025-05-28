package ru.job4j.algo;

import java.util.Stack;

public class Brackets {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '{' && close == '}')
                || (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([])";

        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
    }
}
/*
Алгоритм проходит по всем символам строки один раз, где n — длина строки.
Каждое действие (добавление в стек, удаление из стека и проверка соответствия)
выполняется за константное время O(1).
Поэтому общая сложность алгоритма будет линейной, то есть O(n).
-----------------------------------------------------------------------------
В наихудшем случае (например, если строка состоит только из открывающих скобок,
таких как "(((((") все символы будут помещены в стек и если строка корректно
сбалансирована и состоит из n открывающих скобок в памяти будет храниться все n элементов.
Поэтому стек все равно займет O(n) памяти.
*/
