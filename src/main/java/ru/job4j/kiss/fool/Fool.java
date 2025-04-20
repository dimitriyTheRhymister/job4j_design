package ru.job4j.kiss.fool;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        new Fool().playGame();
    }

    public void playGame() {
        System.out.println("Game FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);

        while (startAt < 20) {
            System.out.println(getExpectedAnswer(startAt++));
            String answer;
            try {
                answer = input.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            String answerString = getExpectedAnswer(startAt++);

            boolean isAnswerCorrect = answerString.equals(answer);
            boolean isNumberCorrect = true;

            if (!isAnswerCorrect) {
                try {
                    int answerInt = Integer.parseInt(answer);
                    isNumberCorrect = (answerInt == startAt);
                } catch (NumberFormatException e) {
                    isNumberCorrect = false;
                }
            }
            if (!isAnswerCorrect && !isNumberCorrect) {
                System.out.println("Mistake. Start again.");
                startAt = 1;
            }
        }
    }

    public String getExpectedAnswer(int number) {
        String s;
        if (number % 3 == 0 && number % 5 == 0) {
            s = "FizzBuzz";
        } else if (number % 3 == 0) {
            s = "Fizz";
        } else if (number % 5 == 0) {
            s = "Buzz";
        } else {
            s = String.valueOf(number);
        }
        return s;
    }
}